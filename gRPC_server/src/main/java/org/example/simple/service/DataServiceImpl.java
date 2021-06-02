/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.simple.service;

import org.example.simple.gRPC.MessagesServiceOuterClass;
import org.example.simple.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.HashMap;


@Service
public class DataServiceImpl {

    private final DataRepository repository;

    @Autowired
    public DataServiceImpl(DataRepository repository) {
        this.repository = repository;
    }


    public void addData(MessagesServiceOuterClass.RequestInputDataMessage request) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres");
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO data  (id_message, time, queue_name, body_message)  VALUES (" + request.getId()
                + ",'" + Instant.now().toString()  + "','" + request.getQueueName() + "','" + request.getBodyMessage() + "')");
        statement.close();

    }
}
