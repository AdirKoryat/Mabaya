package mabaya.controllers;

import mabaya.data.ProductEntity;
import mabaya.logic.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(
            path="/createStub",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductEntity> createStubProducts() {
        return productService.createStubProducts();
    }

    @RequestMapping(
            path="/deleteAllProducts",
            method = RequestMethod.DELETE)
    public void deleteAllProducts() {
        productService.deleteAll();
    }

}
