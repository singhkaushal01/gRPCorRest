package com.poc.grpc;

import com.poc.grpc.product.*;

import javax.annotation.*;
import java.util.*;

public class SampleDataLoadingService {

    private Map<ProductId, Product> productMap = new HashMap<>();

    public void loadDataSet() {
        Random rnd = new Random(System.currentTimeMillis());

        Scanner scanner = new Scanner(SampleDataLoadingService.class.getClassLoader().getResourceAsStream("product_data_20_attributes.csv"));
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            Product productRec = SampleDataLoadingService.csv2DTO(scanner.nextLine());
            ProductId productId = ProductId.newBuilder().setId(productRec.getId()).build();
            productMap.put(productId, productRec);
        }
        scanner.close();
    }

    public Map<ProductId, Product> getProductMap(){
        return productMap;
    }

    private static Product csv2DTO(String record){
        String [] attributes = record.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        Product product = Product.newBuilder()
                .setId(Integer.parseInt(attributes[0]))
                .setName(attributes[1])
                .setPrice(Float.parseFloat(attributes[2]))
                .setStock(Integer.parseInt(attributes[3]))
                .setCategory(attributes[4])
                .setBrand(attributes[5])
                .setWeight(attributes[6])
                .setColor(attributes[7])
                .setManufacturer(attributes[8])
                .setReleaseDate(attributes[9])
                .setRating(Float.parseFloat(attributes[10]))
                .setAttribute1(attributes[11])
                .setAttribute2(attributes[12])
                .setAttribute3(attributes[13])
                .setAttribute4(attributes[14])
                .setAttribute5(attributes[15])
                .setAttribute6(attributes[16])
                .setAttribute7(attributes[17])
                .setAttribute8(attributes[18])
                .setAttribute9(attributes[19])
                .setAttribute10(attributes[20])
                .setAttribute11(attributes[21])
                .setAttribute12(attributes[22])
                .setAttribute13(attributes[23])
                .setAttribute14(attributes[24])
                .setAttribute15(attributes[25])
                .setAttribute16(attributes[26])
                .setAttribute17(attributes[27])
                .setAttribute18(attributes[28])
                .setAttribute19(attributes[29])
                .setAttribute20(attributes[30])
                .build();

        return product;
    }

}
