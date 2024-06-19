package org.lgz.memosbackend.database.dao;

import org.lgz.memosbackend.database.model.MemoBookModel;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoBookRepository extends ReactiveNeo4jRepository<MemoBookModel,Long> {
}
