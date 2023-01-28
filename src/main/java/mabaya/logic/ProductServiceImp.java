package mabaya.logic;

import mabaya.data.ProductDao;
import mabaya.data.ProductEntity;
import mabaya.utils.StubFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImp implements ProductService {
    private final ProductDao productDao;
    @Autowired
    public ProductServiceImp(ProductDao productDao) {
        this.productDao = productDao;
    }
    @Override
    public List<ProductEntity> createStubProducts() {
        final List<ProductEntity> products = StubFunctions.createRandomProducts();
        productDao.saveAll(products);
        return products;
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
    }
}
