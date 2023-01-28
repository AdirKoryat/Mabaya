package mabaya.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import java.util.List;
@ToString
@Builder
@Getter
@Setter
@Document(collection = "campaign")
public class CampaignEntity {
    @Id
    private String campaignId;
    private List<ProductEntity> products;
    private LocalDate startDate;
    private Double bid;
    private String name;
    private boolean isActive;
}
