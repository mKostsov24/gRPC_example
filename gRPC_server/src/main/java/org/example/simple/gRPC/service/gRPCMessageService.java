package org.example.simple.gRPC.service;

import io.grpc.stub.StreamObserver;

import org.example.simple.gRPC.MessagesServiceGrpc;
import org.example.simple.gRPC.MessagesServiceOuterClass;
import org.example.simple.model.Data;
import org.example.simple.repository.DataRepository;
import org.example.simple.service.DataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;

@Service
public class gRPCMessageService extends MessagesServiceGrpc.MessagesServiceImplBase {

    private final DataServiceImpl service;
    private final DataRepository repository;
    private static final Logger log =
            LoggerFactory.getLogger(gRPCMessageService.class);
    @Autowired
    public gRPCMessageService(DataServiceImpl service, DataRepository repository) {
        this.service = service;
        this.repository = repository;

    }

    @Override
    public void addData(MessagesServiceOuterClass.RequestInputDataMessage request,
                        StreamObserver<MessagesServiceOuterClass.ResponseInputDataMessage> responseObserver) throws SQLException {
        log.info(String.valueOf(request));
        service.addData(request);
        MessagesServiceOuterClass.ResponseInputDataMessage response = MessagesServiceOuterClass
                .ResponseInputDataMessage.newBuilder()
                .setMessage("Succsess")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getData(MessagesServiceOuterClass.RequestOutputDataMessage request,
                        StreamObserver<MessagesServiceOuterClass.ResponseOutputDataMessage> responseObserver) {

        if(request.getMessage().equals("0")){
            repository.getAll().forEach(d->{
                MessagesServiceOuterClass.ResponseOutputDataMessage response = MessagesServiceOuterClass
                        .ResponseOutputDataMessage.newBuilder()
                        .setId(d.getId())
                        .setIdMessage(d.getIdMessage())
                        .setQueueName(d.getQueueName())
                        .setBodyMessage(d.getBodyMessage())
                        .setTime(d.getTime().toString())
                        .build();

                responseObserver.onNext(response);
            });
            responseObserver.onCompleted();
        } else if(request.getMessage().equals("queue-1") || request.getMessage().equals("queue-1")){
            repository.getAllByQueueName(request.getMessage()).forEach(d->{
                MessagesServiceOuterClass.ResponseOutputDataMessage response = MessagesServiceOuterClass
                        .ResponseOutputDataMessage.newBuilder()
                        .setId(d.getId())
                        .setIdMessage(d.getIdMessage())
                        .setQueueName(d.getQueueName())
                        .setBodyMessage(d.getBodyMessage())
                        .setTime(d.getTime().toString())
                        .build();

                responseObserver.onNext(response);
            });

        }else if (request.getMessage().contains("-")){
          String[] dataList =  request.getMessage().split(" ");
            Instant from  = Instant.parse(dataList[0]);
            Instant to = Instant.parse(dataList[1]);
          repository.getAllByDate(from, to).forEach(d->{
              MessagesServiceOuterClass.ResponseOutputDataMessage response = MessagesServiceOuterClass
                      .ResponseOutputDataMessage.newBuilder()
                      .setId(d.getId())
                      .setIdMessage(d.getIdMessage())
                      .setQueueName(d.getQueueName())
                      .setBodyMessage(d.getBodyMessage())
                      .setTime(d.getTime().toString())
                      .build();

              responseObserver.onNext(response);
          });
        }
        else {
        Data outputLogData = repository.getById(Integer.parseInt(request.getMessage()));

        MessagesServiceOuterClass.ResponseOutputDataMessage response = MessagesServiceOuterClass
                .ResponseOutputDataMessage.newBuilder()
                .setId(outputLogData.getId())
                .setIdMessage(outputLogData.getIdMessage())
                .setQueueName(outputLogData.getQueueName())
                .setBodyMessage(outputLogData.getBodyMessage())
                .setTime(outputLogData.getTime().toString())
                .build();

        responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
