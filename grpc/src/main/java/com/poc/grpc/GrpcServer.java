package com.poc.grpc;

import io.grpc.*;

import java.io.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class GrpcServer {

    private final Server server;

	public static void main(String[] args) throws Exception {
        GrpcServer server = new GrpcServer(8081);
        server.start();
        server.blockUntilShutdown();
	}

    public GrpcServer(int port) throws IOException {
        this(ServerBuilder.forPort(port));
    }

    public GrpcServer(ServerBuilder<?> serverBuilder) {

        BindableService productService = new ProductController();
        serverBuilder
                .addService(productService);
        server = serverBuilder.build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Server started, listening on " + server.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    GrpcServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
