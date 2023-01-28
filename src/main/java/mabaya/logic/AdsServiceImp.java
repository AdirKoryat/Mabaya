package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.data.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdsServiceImp implements AdsService{
    private final CampaignService campaignService;
    @Autowired
    public AdsServiceImp(CampaignService campaignService) {
        this.campaignService = campaignService;
    }
    @Override
    public CampaignToReturnBoundary createCampaign(CampaignBoundary campaignBoundary) {
        return campaignService.createCampaign(campaignBoundary);
    }

    @Override
    public ProductEntity serveAd(String category) {
        return campaignService.serveAd(category);
    }
}
