package mabaya.logic;

import mabaya.data.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> createStubProducts();

    void deleteAll();
}
