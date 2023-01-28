package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.data.ProductEntity;

public interface AdsService {
    CampaignToReturnBoundary createCampaign(CampaignBoundary campaignBoundary);
    ProductEntity serveAd(String category);
}
