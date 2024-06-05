package org.lgz.memosbackend.database.dao;

import org.lgz.memosbackend.database.model.UserModel;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveNeo4jRepository<UserModel,Long> {
    Mono<UserModel> findByUsername(String username);
}
