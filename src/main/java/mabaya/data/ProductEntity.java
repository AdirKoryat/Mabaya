package mabaya.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "product")
public class ProductEntity {
    @Id
    private String productSerialNumber;
    private String title;
    private String category;
    private Double price;
}
