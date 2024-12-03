package com.example.snagicky.api.repo;

import com.example.snagicky.api.model.Types;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesRepo extends CrudRepository<Types, Long> {
    @Query(value = "SELECT * FROM types WHERE (:Id is null or types.id = :Id) and (:Name is null or INSTR(types.name,:Name)) and (:Type is null or types.card_type = :Type) ORDER BY types.name DESC LIMIT :Limit OFFSET :Offset", nativeQuery = true)
    Iterable<Types> GetType(
            @Param("Id") Long id,
            @Param("Name") String name,
            @Param("Type") Integer card_type,

            @Param("Offset") Integer offset,
            @Param("Limit") Integer limit
    );

}
