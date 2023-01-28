package mabaya.logic;

import lombok.extern.log4j.Log4j2;
import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.data.CampaignDao;
import mabaya.data.CampaignEntity;
import mabaya.data.ProductDao;
import mabaya.data.ProductEntity;
import mabaya.errors.BadRequestException;
import mabaya.errors.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CampaignServiceImp implements CampaignService {
    private final ProductDao productDao;
    private final CampaignDao campaignDao;

    public CampaignServiceImp(ProductDao productDao, CampaignDao campaignDao) {
        this.productDao = productDao;
        this.campaignDao = campaignDao;
    }
    @Override
    public CampaignToReturnBoundary createCampaign(CampaignBoundary campaignBoundary) {
        final CampaignEntity campaignEntity = CampaignToEntity(campaignBoundary);
        campaignDao.save(campaignEntity);
        return CampaignToReturnToBoundary(campaignEntity);
    }

    @Override
    public ProductEntity serveAd(String category) {
        List<ProductEntity> candidateProducts = campaignDao.findAll()
                .stream()
                .filter(CampaignEntity::isActive)
                .filter(campaignEntity -> campaignEntity.getProducts()
                        .stream().anyMatch(productEntity -> productEntity
                                .getCategory()
                                .equalsIgnoreCase(category)))
                .max(Comparator.comparing(CampaignEntity::getBid))
                .orElse(campaignDao.findAll()
                        .stream()
                        .filter(CampaignEntity::isActive)
                        .max(Comparator.comparing(CampaignEntity::getBid))
                        .orElseThrow(() ->new NotFoundException(
                                String.format("No promoted product found that match %s or in any active campaign.", category))))
                .getProducts();

        if (candidateProducts.stream().anyMatch(productEntity -> productEntity.getCategory().equalsIgnoreCase(category))) {
            Optional<ProductEntity> productToReturn =  candidateProducts.stream()
                    .filter(productEntity -> productEntity.getCategory().equalsIgnoreCase(category))
                    .findFirst();
            if(productToReturn.isPresent())
                return productToReturn.get();
        }
        if (candidateProducts.isEmpty())
            throw new NotFoundException("No products were found!");

        return candidateProducts.get(0);
    }

    @Override
    public void deleteAll() {
        campaignDao.deleteAll();
    }

    private CampaignEntity CampaignToEntity(CampaignBoundary campaignBoundary) {
        List<ProductEntity> products = productDao.findAllById(campaignBoundary.getProductsIdentifiers());
        if (products.size() != campaignBoundary.getProductsIdentifiers().size())
            throw new BadRequestException("Not all products were found, please provide correct id identifiers");
        CampaignEntity campaignEntity = CampaignEntity.builder()
                .products(products)
                .bid(campaignBoundary.getBid())
                .name(campaignBoundary.getName())
                .startDate(convertStartDate(campaignBoundary.getStartDate()))
                .build();
        campaignEntity.setActive(isCampaignActive(campaignEntity.getStartDate()));
        return campaignEntity;
    }

    private CampaignToReturnBoundary CampaignToReturnToBoundary(CampaignEntity campaignEntity) {
        return CampaignToReturnBoundary.builder()
                .name(campaignEntity.getName())
                .startDate(campaignEntity.getStartDate())
                .products(campaignEntity.getProducts())
                .bid(campaignEntity.getBid())
                .isActive(campaignEntity.isActive())
                .build();
    }

    private LocalDate convertStartDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            throw new BadRequestException("The Date must be in dd/MM/yyyy format!");
        }
    }

    private boolean isCampaignActive(LocalDate date) {
        final LocalDate dateNow =LocalDate.now();
        final Period period = Period.between(date, dateNow);
        final int daysDiff = Math.abs(period.getDays());
        return daysDiff <= 10;
    }
}
