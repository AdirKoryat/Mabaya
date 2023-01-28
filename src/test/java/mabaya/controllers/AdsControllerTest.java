package mabaya.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import mabaya.boundary.CampaignBoundary;
import mabaya.boundary.CampaignToReturnBoundary;
import mabaya.logic.AdsServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdsController.class)
public class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdsServiceImp adsServiceImp;




@Test
    public void create_campaign_success() throws Exception {

        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        CampaignBoundary campaignBoundary = CampaignBoundary.builder()
                .bid(12.)
                .name("campaign")
                .productsIdentifiers(List.of("1", "2"))
                .startDate(dateNow)
                .build();


        Mockito.when(adsServiceImp.createCampaign(campaignBoundary)).thenReturn(CampaignToReturnBoundary.builder().build());
        mockMvc.perform(post("/createCampaign").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(campaignBoundary)))
            .andExpect(status().isOk())
            .andDo(print());


    }
}
