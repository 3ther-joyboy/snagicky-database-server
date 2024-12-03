package com.example.snagicky.api.repo;

import com.example.snagicky.api.model.Editions;
import com.example.snagicky.api.model.Passives;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassivesRepo extends CrudRepository<Passives, Long> {
    @Query(value = "SELECT * FROM passives WHERE (:Id is null or passives.id = :Id) and (:Str is null or INSTR(passives.description,:Str) or INSTR(passives.name, :Str))", nativeQuery = true)
    Iterable<Passives> GetPassive(@Param(value = "Id") Long id, @Param(value = "Str") String str);
}
