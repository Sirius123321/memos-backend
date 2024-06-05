package org.lgz.memosbackend.database.dao;

import org.lgz.memosbackend.database.model.MemoModel;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends ReactiveNeo4jRepository<MemoModel,Long> {
}
