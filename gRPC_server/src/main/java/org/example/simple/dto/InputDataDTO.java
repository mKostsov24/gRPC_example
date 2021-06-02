package org.example.simple.dto;

import lombok.Data;

@Data
public class InputDataDTO {
    private String id;
    private String queueName;
    private String bodyMessage;

    public InputDataDTO(String id, String queueName, String bodyMessage) {
        this.id = id;
        this.queueName = queueName;
        this.bodyMessage = bodyMessage;
    }
}
