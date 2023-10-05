package com.poc.rest;

import jakarta.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SampleDataLoadingService {

    private Map<Integer, Product> productMap = new HashMap<>();

    @PostConstruct
    public void loadDataSet() {
        Random rnd = new Random(System.currentTimeMillis());

        Scanner scanner = new Scanner(SampleDataLoadingService.class.getClassLoader().getResourceAsStream("product_data_20_attributes.csv"));
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            Product productRec = SampleDataLoadingService.csv2DTO(scanner.nextLine());
            while (productMap.containsKey(productRec.getId()))
                productRec.setId(rnd.nextInt(10000000));
            productMap.put(productRec.getId(), productRec);
        }
        scanner.close();
    }

    public Map<Integer, Product> getProductMap(){
        return productMap;
    }

    private static Product csv2DTO(String record){
        String [] attributes = record.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        Product product = new Product();
        product.setId(Integer.parseInt(attributes[0]));
        product.setName(attributes[1]);
        product.setPrice(Float.parseFloat(attributes[2]));
        product.setStock(Integer.parseInt(attributes[3]));
        product.setCategory(attributes[4]);
        product.setBrand(attributes[5]);
        product.setWeight(attributes[6]);
        product.setColor(attributes[7]);
        product.setManufacturer(attributes[8]);
        product.setReleaseDate(attributes[9]);
        product.setRating(Float.parseFloat(attributes[10]));
        product.setAttribute1(attributes[11]);
        product.setAttribute2(attributes[12]);
        product.setAttribute3(attributes[13]);
        product.setAttribute4(attributes[14]);
        product.setAttribute5(attributes[15]);
        product.setAttribute6(attributes[16]);
        product.setAttribute7(attributes[17]);
        product.setAttribute8(attributes[18]);
        product.setAttribute9(attributes[19]);
        product.setAttribute10(attributes[20]);
        product.setAttribute11(attributes[21]);
        product.setAttribute12(attributes[22]);
        product.setAttribute13(attributes[23]);
        product.setAttribute14(attributes[24]);
        product.setAttribute15(attributes[25]);
        product.setAttribute16(attributes[26]);
        product.setAttribute17(attributes[27]);
        product.setAttribute18(attributes[28]);
        product.setAttribute19(attributes[29]);
        product.setAttribute20(attributes[30]);
        return product;
    }

}
