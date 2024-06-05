package org.lgz.memosbackend.database.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node("Memo")
public class MemoModel {
    @Id private Long id;

    private String content;

    @Relationship(type = "OWNED_BY", direction = Relationship.Direction.OUTGOING)
    private UserModel owner;

    @Relationship(type = "SHARED_WITH", direction = Relationship.Direction.OUTGOING)
    private List<UserModel> sharedWith;
}
