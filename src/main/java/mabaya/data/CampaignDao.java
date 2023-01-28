package mabaya.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignDao extends MongoRepository<CampaignEntity, String> {
    //CRUD
}
