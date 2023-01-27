package mabaya.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDao extends MongoRepository<ProductEntity, String> {
    //CRUD
}
