package org.example.simple.repository;

import org.example.simple.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {

    @Query("SELECT d FROM Data d")
    List<Data> getAll();

    Data getById(int id);

    List<Data> getAllByQueueName(String queueName);

    @Query("select d from Data d where d.time between :from and :to ")
    List<Data> getAllByDate(@Param("from") Instant from, @Param("to") Instant to);

}
