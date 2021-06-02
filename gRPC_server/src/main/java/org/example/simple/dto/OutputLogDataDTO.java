package org.example.simple.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OutputLogDataDTO {
    private int id;
    private int idMassege;
    private Instant time;
    private String queueName;
    private String bodyMessage;

    public OutputLogDataDTO(org.example.simple.model.Data data) {
        this.id = data.getId();
        this.idMassege = data.getIdMessage();
        this.time = data.getTime();
        this.queueName = data.getQueueName();
        this.bodyMessage = data.getBodyMessage();
    }
}
