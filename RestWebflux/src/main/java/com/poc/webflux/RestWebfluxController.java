package com.poc.webflux;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.util.*;
import java.util.stream.*;

@RestController
public class RestWebfluxController {

    @Autowired
    private SampleDataLoadingService sampleDataLoadingService;

    @GetMapping(value = "/products/{id}")
//  public Mono<ResponseEntity<Product>> getProductDetails(@PathVariable("id") int id) {
    public Product getProductDetails(@PathVariable("id") int id) {

        return sampleDataLoadingService.getProductMap().entrySet().stream()
                .filter(entity -> id == entity.getKey())
                .map(filtered -> filtered.getValue())
                .findFirst().orElse(null);
    }

    @GetMapping(value = "/products")
//  public Mono<ResponseEntity<Product>> getProductDetails(@PathVariable("id") int id) {
    public List<Product> getListOfProduct(@RequestParam("count") int count, @RequestParam("offset") int offset) {

        return sampleDataLoadingService.getProductMap().entrySet()
                .stream().skip(offset).limit(count)
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

    }

    @GetMapping("/products/:ids")
    public Mono<ResponseEntity<Set<Integer>>> getAllProductsId() {
        return Mono.just(new ResponseEntity<Set<Integer>>(sampleDataLoadingService.getProductMap().keySet(), HttpStatus.OK));
    }
}
