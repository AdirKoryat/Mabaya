package mabaya.boundary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class CampaignBoundary {
    private List<String> productsIdentifiers;
    private String startDate;
    private Double bid;
    private String name;
}
