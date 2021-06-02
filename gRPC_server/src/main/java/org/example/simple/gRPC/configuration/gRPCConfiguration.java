package org.example.simple.gRPC.configuration;

import io.grpc.Server;
import io.grpc.ServerBuilder;


import org.example.simple.gRPC.service.gRPCMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Configuration
public class gRPCConfiguration {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(gRPCConfiguration.class);
    gRPCMessageService gRPCService;

    @Autowired
    public gRPCConfiguration(gRPCMessageService gRPCMessageService) {
        this.gRPCService = gRPCMessageService;
    }

    @PostConstruct
    public void startgRPCServer() throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8085)
                .addService(gRPCService)
                .build();

        server.start();
        LOGGER.info("Server Grpc started");
        server.awaitTermination();
    }
}
