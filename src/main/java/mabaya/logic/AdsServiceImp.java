package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.data.CampaignEntity;
import mabaya.data.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdsServiceImp implements AdsService{
    private final CampaignService campaignService;
    private final ProductService productService;
    @Autowired
    public AdsServiceImp(CampaignService campaignService, ProductService productService) {
        this.campaignService = campaignService;
        this.productService = productService;
    }
    @Override
    public CampaignEntity createCampaign(CampaignBoundary campaign) {
        return campaignService.createCampaign(campaign);
    }

    @Override
    public ProductEntity serveAd(String category) {
        return campaignService.serveAd(category);
    }
}
