package mabaya.controllers;

import mabaya.logic.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CampaignController {
    private final CampaignService campaignService;
    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @RequestMapping(
            path="/deleteAllCampaigns",
            method = RequestMethod.DELETE)
    public void deleteAllCampaigns() {
        campaignService.deleteAll();
    }
}
