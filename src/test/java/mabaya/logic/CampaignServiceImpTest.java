package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.data.CampaignDao;
import mabaya.data.ProductDao;
import mabaya.data.ProductEntity;
import mabaya.errors.BadRequestException;
import mabaya.errors.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static mabaya.utils.StubFunctions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CampaignServiceImpTest {

    CampaignServiceImp campaignServiceImp;
    @MockBean
    private ProductDao productDao;
    @MockBean
    private CampaignDao campaignDao;

    @BeforeEach
    public void setUp() {
        this.campaignServiceImp = new CampaignServiceImp(productDao, campaignDao);
        Mockito.when(productDao.findAllById(List.of("1")))
                .thenReturn(List.of(ProductEntity.builder()
                        .productSerialNumber("1")
                        .title("blue jeans")
                        .category("clothing")
                        .price(20.)
                        .build()));
    }

    @Test
    public void create_active_campaign_success() {

        Mockito.when(campaignDao.save(any())).thenReturn(null);

        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        CampaignBoundary campaignBoundary = CampaignBoundary.builder()
                .bid(12.)
                .name("campaign")
                .productsIdentifiers(List.of("1"))
                .startDate(dateNow)
                .build();

        CampaignToReturnBoundary campaignToReturnBoundary = campaignServiceImp.createCampaign(campaignBoundary);
        assertEquals(campaignToReturnBoundary.getBid(),campaignBoundary.getBid());
        assertTrue(campaignToReturnBoundary.isActive());
        assertEquals(campaignToReturnBoundary.getName(),campaignBoundary.getName());
        assertEquals(campaignToReturnBoundary.getProducts().size(),1);
        assertEquals(campaignToReturnBoundary.getStartDate(), LocalDate.parse(dateNow, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    @Test
    public void create_non_active_campaign_success() {

        Mockito.when(campaignDao.save(any())).thenReturn(null);

        String dateNow = LocalDate.now().minusDays(11).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        CampaignBoundary campaignBoundary = CampaignBoundary.builder()
                .bid(12.)
                .name("campaign")
                .productsIdentifiers(List.of("1"))
                .startDate(dateNow)
                .build();

        CampaignToReturnBoundary campaignToReturnBoundary = campaignServiceImp.createCampaign(campaignBoundary);
        assertFalse(campaignToReturnBoundary.isActive());
    }

    @Test
    public void create_campaign_with_invalid_date_throw() {

        Mockito.when(campaignDao.save(any())).thenReturn(null);

        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        CampaignBoundary campaignBoundary = CampaignBoundary.builder()
                .bid(12.)
                .name("campaign")
                .productsIdentifiers(List.of("1"))
                .startDate(dateNow)
                .build();

        BadRequestException badRequestException = assertThrows(BadRequestException.class,
                ()-> campaignServiceImp.createCampaign(campaignBoundary));
        assertEquals(badRequestException.getMessage(), "The Date must be in dd/MM/yyyy format!");
    }

    @Test
    public void create_campaign_with_invalid_product_identifiers_throw() {

        Mockito.when(campaignDao.save(any())).thenReturn(null);

        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        CampaignBoundary campaignBoundary = CampaignBoundary.builder()
                .bid(12.)
                .name("campaign")
                .productsIdentifiers(List.of("1", "2"))
                .startDate(dateNow)
                .build();

        BadRequestException badRequestException = assertThrows(BadRequestException.class,
                ()-> campaignServiceImp.createCampaign(campaignBoundary));
        assertEquals(badRequestException.getMessage(),
                "Not all products were found, please provide correct id identifiers");
    }

    @Test
    public void serve_ad_with_category_found_return_category_product_with_highest_bid() {
        Mockito.when(campaignDao.findAll()).thenReturn(createActiveTestCampaigns());
        ProductEntity expectedProductEntity = ProductEntity.builder()
                .productSerialNumber("1")
                .title("blue jeans")
                .category("clothing")
                .price(20.)
                .build();

        ProductEntity actualProductEntity = campaignServiceImp.serveAd("clothing");
        assertEquals(actualProductEntity, expectedProductEntity);

    }

    @Test
    public void serve_ad_with_category_not_found_return_product_with_highest_bid() {
        Mockito.when(campaignDao.findAll()).thenReturn(createActiveTestCampaigns());
        ProductEntity expectedProductEntity = ProductEntity.builder()
                .productSerialNumber("3")
                .title("blandstone")
                .category("shoes")
                .price(35.)
                .build();

        ProductEntity actualProductEntity = campaignServiceImp.serveAd("headphones");
        assertEquals(actualProductEntity, expectedProductEntity);

    }

    @Test
    public void serve_ad_with_no_active_campaigns_throw() {
        String category = "headphones";
        Mockito.when(campaignDao.findAll()).thenReturn(createNonActiveTestCampaign());

                NotFoundException notFoundException = assertThrows(NotFoundException.class,
                ()-> campaignServiceImp.serveAd(category));
        assertEquals(notFoundException.getMessage(),
                String.format("No promoted product found that match %s or in any active campaign.", category));

    }


    @Test
    public void serve_ad_with_highest_bid_campaign_with_no_products_throw() {
        String category = "headphones";
        Mockito.when(campaignDao.findAll()).thenReturn(createActiveTestCampaignWithNoProducts());

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                ()-> campaignServiceImp.serveAd(category));
        assertEquals(notFoundException.getMessage(),
                "No products were found!");

    }
}