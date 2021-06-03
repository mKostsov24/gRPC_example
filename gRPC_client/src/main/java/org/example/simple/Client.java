package org.example.simple;


import org.example.simple.gRPC.MessagesServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main( String[] args )
    {
        addData();
        getLogData();
    }

    public static void getLogData(){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085")
                .usePlaintext().build();
        org.example.simple.gRPC.MessagesServiceGrpc.MessagesServiceBlockingStub stub  = org.example.simple.gRPC.MessagesServiceGrpc.newBlockingStub(channel);
        org.example.simple.gRPC.MessagesServiceOuterClass.RequestOutputDataMessage requestOutputDataMessage =  org.example.simple.gRPC.MessagesServiceOuterClass.RequestOutputDataMessage.newBuilder().setMessage("0").build();
//        org.example.simple.gRPC.MessagesServiceOuterClass.RequestOutputDataMessage requestOutputDataMessage =  org.example.simple.gRPC.MessagesServiceOuterClass.RequestOutputDataMessage.newBuilder().setMessage("3").build();
//        org.example.simple.gRPC.MessagesServiceOuterClass.RequestOutputDataMessage requestOutputDataMessage =  org.example.simple.gRPC.MessagesServiceOuterClass.RequestOutputDataMessage.newBuilder().setMessage("2021-05-24T21:00:00Z 2021-05-25T21:00:00Z").build();
        Iterator<MessagesServiceOuterClass.ResponseOutputDataMessage> responseOutputDataMessage = stub.getData(requestOutputDataMessage);
        while(responseOutputDataMessage.hasNext()){
            System.out.println(responseOutputDataMessage.next());
        }
        channel.shutdownNow();
    }

    public static void addData(){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085")
                .usePlaintext().build();
        org.example.simple.gRPC.MessagesServiceGrpc.MessagesServiceBlockingStub stub  = org.example.simple.gRPC.MessagesServiceGrpc.newBlockingStub(channel);
        org.example.simple.gRPC.MessagesServiceOuterClass.RequestInputDataMessage  requestInputDataMessage =  org.example.simple.gRPC.MessagesServiceOuterClass.RequestInputDataMessage.newBuilder().setId("87").setBodyMessage("1i7").setQueueName("queue-2").build();
        org.example.simple.gRPC.MessagesServiceOuterClass.ResponseInputDataMessage responseInputDataMessage = stub.addData(requestInputDataMessage);
        System.out.println(responseInputDataMessage);
        org.example.simple.gRPC.MessagesServiceOuterClass.RequestInputDataMessage  requestInputDataMessageTwo =  org.example.simple.gRPC.MessagesServiceOuterClass.RequestInputDataMessage.newBuilder().setId("77").setBodyMessage("1i7").setQueueName("queue-1").build();
        org.example.simple.gRPC.MessagesServiceOuterClass.ResponseInputDataMessage responseInputDataMessageTWo = stub.addData(requestInputDataMessageTwo);
        System.out.println(responseInputDataMessageTWo);

        channel.shutdownNow();
    }
}
