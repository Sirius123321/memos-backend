package org.lgz.memosbackend.database.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data  // lombok注解
@Node("User")
public class UserModel {
    @Id @GeneratedValue Long id;
    String username;
    String password;
    @Relationship(type = "OWNED_BY", direction = Relationship.Direction.INCOMING)
    private List<MemoModel> memos;

    @Relationship(type = "SHARED_WITH", direction = Relationship.Direction.INCOMING)
    private List<MemoModel> sharedMemos;


}
