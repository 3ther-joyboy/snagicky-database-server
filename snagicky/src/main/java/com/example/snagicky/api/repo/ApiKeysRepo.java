package com.example.snagicky.api.repo;

import com.example.snagicky.api.model.ApiKeys;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeysRepo extends CrudRepository<ApiKeys,Long> {
    @Query(value = "SELECT EXISTS(SELECT * FROM api_keys WHERE api_keys.api_key = :Key );", nativeQuery = true)
    long Check(@Param(value = "Key") String apiKey);
}
