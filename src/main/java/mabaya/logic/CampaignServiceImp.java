package mabaya.logic;

import mabaya.boundary.CampaignBoundary;
import mabaya.data.CampaignEntity;
import mabaya.data.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CampaignServiceImp implements CampaignService{
    @Override
    public CampaignEntity createCampaign(CampaignBoundary campaign) {
        //TODO: Implement logic of create campaign method
        return CampaignEntity.builder().products(Collections.singletonList(ProductEntity.builder().build())).build();
    }

    @Override
    public ProductEntity serveAd(String category) {
        //TODO: Implement logic of serve ad method
        return ProductEntity.builder().build();
    }
}
