package mabaya.controllers;

import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.data.ProductEntity;
import mabaya.logic.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdsController {
    private final AdsService adsService;

    @Autowired
    public AdsController(AdsService adsService){
        this.adsService = adsService;
    }

@PostMapping(path ="/createCampaign" )
    public CampaignToReturnBoundary createCampaign(@RequestBody CampaignBoundary campaignBoundary) {
        return adsService.createCampaign(campaignBoundary);
    }

    @RequestMapping(
            path="/serve/{category}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductEntity serveAd(@PathVariable("category") String category) {
        return adsService.serveAd(category);
}

}
