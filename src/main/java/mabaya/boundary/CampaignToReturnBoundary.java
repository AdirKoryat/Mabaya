package mabaya.boundary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mabaya.data.ProductEntity;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class CampaignToReturnBoundary {
    private List<ProductEntity> products;
    private LocalDate startDate;
    private Double bid;
    private String name;
    private boolean isActive;
}
