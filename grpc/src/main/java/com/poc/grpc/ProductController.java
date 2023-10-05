package com.poc.grpc;

import com.poc.grpc.product.*;
import com.poc.grpc.product.ProductServiceGrpc.ProductServiceImplBase;
import io.grpc.stub.*;

import java.util.*;
import java.util.logging.*;
import java.util.stream.*;


public class ProductController extends ProductServiceImplBase {

    private static final Logger log = Logger.getLogger(ProductController.class.getName());
    private Map<ProductId, Product> productMap = new HashMap<>();
    ProductController(){
        SampleDataLoadingService sampleDataLoadingService = new SampleDataLoadingService();
        sampleDataLoadingService.loadDataSet();
        productMap = sampleDataLoadingService.getProductMap();
    }

    @Override
    public void getProduct(ProductId request, StreamObserver<Product> responseObserver) {

        Product product = productMap.entrySet().stream()
                                .filter(entry -> entry.getKey().getId() == request.getId())
                                .map(filtered -> filtered.getValue())
                                .findFirst().orElse(null);

        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductIds(com.google.protobuf.Empty request, StreamObserver<ProductIdList> responseObserver) {
//        productMap.entrySet().stream()
//                .forEach(entry -> responseObserver.onNext(ProductId.newBuilder()
//                        .setId(entry.getKey()).build()));
        List<ProductId> list =
                productMap.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toList());
        ProductIdList productIdList = ProductIdList.newBuilder().addAllProductId(list).build();

        responseObserver.onNext(productIdList);
        responseObserver.onCompleted();
    }

    public void getProductList(params request, StreamObserver<ProductsList> responseObserver) {

        List<Product> list =  productMap.entrySet()
                .stream().skip(request.getOffset()).limit(request.getCount())
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

        ProductsList productsList = ProductsList.newBuilder().addAllProduct(list).build();
        responseObserver.onNext(productsList);
        responseObserver.onCompleted();

    }


}
