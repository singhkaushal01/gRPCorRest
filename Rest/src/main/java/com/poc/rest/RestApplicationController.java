package com.poc.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
public class RestApplicationController {

    @Autowired
    private SampleDataLoadingService sampleDataLoadingService;

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int productId){
        return sampleDataLoadingService.getProductMap().entrySet()
                .stream().filter(entry -> entry.getKey() == productId)
                .map(filtered -> filtered.getValue())
                .findFirst().orElse(null);

    }

    @GetMapping(value = "/products")
    public List<Product> getListOfProduct(@RequestParam("count") int count, @RequestParam("offset") int offset) {

        return sampleDataLoadingService.getProductMap().entrySet()
                .stream().skip(offset).limit(count)
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

    }

    @GetMapping("/products/:ids")
    public Set<Integer> getAllProductsId(){
        return sampleDataLoadingService.getProductMap().keySet();
    }
}
