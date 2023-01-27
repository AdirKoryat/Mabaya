package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.data.CampaignEntity;
import mabaya.data.ProductEntity;

public interface CampaignService {
    CampaignEntity createCampaign(CampaignBoundary campaign);

    ProductEntity serveAd(String category);
}
