package mabaya.controllers;

import mabaya.boundary.CampaignBoundary;
import mabaya.data.CampaignEntity;
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

    @RequestMapping(
            path="/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CampaignEntity createCampaign(@RequestBody CampaignBoundary campaign) {
        return adsService.createCampaign(campaign);
    }

    @RequestMapping(
            path="/serve/{category}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductEntity serveAd(@PathVariable("category") String category) {
        return adsService.serveAd(category);
}

}
