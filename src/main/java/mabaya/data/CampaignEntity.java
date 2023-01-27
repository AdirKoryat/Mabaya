package mabaya.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Builder
@Getter
@Setter
@Document(collection = "product")
public class CampaignEntity {
    @Id
    private String campaignId;
    private List<ProductEntity> products;
    private Date startDate;
    private Double bid;
    private String name;
    private boolean isActive;
}
