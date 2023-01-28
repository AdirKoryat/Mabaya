package mabaya.boundary;

import lombok.Builder;
import lombok.Data;
import mabaya.data.ProductEntity;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class CampaignToReturnBoundary {
    private List<ProductEntity> products;
    private LocalDate startDate;
    private Double bid;
    private String name;
    private boolean isActive;
}
