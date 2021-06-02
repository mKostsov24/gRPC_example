package org.example.simple.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Data {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int idMessage;
    @Column(nullable = false)
    private Instant time;
    @Column(nullable = false)
    private String  queueName;
    @Column(nullable = false)
    private String bodyMessage;

//    public Data(String queueName, String bodyMessage, int id) {
//        this.idMessage = id;
//        this.time = Instant.now();
//        this.queueName = queueName;
//        this.bodyMessage = bodyMessage;
//    }

}
