package mabaya.utils;

import mabaya.data.ProductEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StubFunctions {

    public static List<ProductEntity> createRandomProducts() {
        return new ArrayList<>(Collections.unmodifiableList(
                Arrays.asList(
                        ProductEntity.builder()
                                .productSerialNumber("1")
                                .title("blue jeans")
                                .category("clothing")
                                .price(20.)
                                .build(),
                        ProductEntity.builder()
                                .productSerialNumber("2")
                                .title("black jeans")
                                .category("clothing")
                                .price(15.)
                                .build(),
                        ProductEntity.builder()
                                .productSerialNumber("3")
                                .title("blandstone")
                                .category("shoes")
                                .price(35.)
                                .build(),
                        ProductEntity.builder()
                                .productSerialNumber("4")
                                .title("Nike")
                                .category("shoes")
                                .price(45.)
                                .build(),
                        ProductEntity.builder()
                                .productSerialNumber("5")
                                .title("Boss")
                                .category("headphones")
                                .price(99.)
                                .build(),
                        ProductEntity.builder()
                                .productSerialNumber("6")
                                .title("Sony")
                                .category("headphones")
                                .price(45.)
                                .build())
        ));
    }
}
