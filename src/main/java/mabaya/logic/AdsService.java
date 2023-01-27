package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.data.CampaignEntity;
import mabaya.data.ProductEntity;

public interface AdsService {
    CampaignEntity createCampaign(CampaignBoundary campaign);
    ProductEntity serveAd(String category);
}
