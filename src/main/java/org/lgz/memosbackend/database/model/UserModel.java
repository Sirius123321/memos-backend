package org.lgz.memosbackend.database.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;

@Data  // lombok注解
@Node("User")
public class UserModel {
    @Id Long id;
    String username;
    String password;

}
