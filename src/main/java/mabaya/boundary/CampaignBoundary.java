package mabaya.boundary;

import lombok.*;

import java.util.List;
@Builder
@Data
public class CampaignBoundary {
    private List<String> productsIdentifiers;
    private String startDate;
    private Double bid;
    private String name;
}
