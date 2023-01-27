package mabaya.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignDao extends MongoRepository<ProductEntity, String> {
    //CRUD
}
