package org.lgz.memosbackend.database.dao;

import org.lgz.memosbackend.database.model.UserModel;
import org.lgz.memosbackend.service.impl.ReactiveUserDetailsServiceImpl;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveNeo4jRepository<UserModel,Long> {
    Mono<UserModel> findByUsername(Mono<String> username);
}
