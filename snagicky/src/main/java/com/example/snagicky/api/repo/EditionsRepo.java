package com.example.snagicky.api.repo;

import com.example.snagicky.api.model.Editions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionsRepo extends CrudRepository<Editions, Long> {

    @Query(value = "SELECT * FROM editions WHERE (:Id is null or editions.id = :Id) and (:Name is null or INSTR(editions.name,:Name)) ORDER BY editions.name DESC LIMIT :Limit OFFSET :Offset" ,nativeQuery = true)
    Iterable<Editions> FindEdition(
            @Param("Id") Long id,
            @Param("Name") String name,
            @Param("Offset") Integer offset,
            @Param("Limit") Integer limit
    );
    @Query(value = "select * from editions where :Id = editions.id", nativeQuery = true)
    Editions FindById(@Param("Id") long id);
}
