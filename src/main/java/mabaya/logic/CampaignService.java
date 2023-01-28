package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.data.ProductEntity;

public interface CampaignService {
    CampaignToReturnBoundary createCampaign(CampaignBoundary campaign);

    ProductEntity serveAd(String category);

    void deleteAll();
}
