package org.lgz.memosbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data transfer object for JWT token for login.
 *
 */
@Data
@AllArgsConstructor
public class JwtDto {
    /**
     * JSON web token string.
     */
    private String token;
    /**
     * Token expiration millisecond timestamp.
     */
    private Long expireAt;
    /**
     * Token issued user id.
     */
    private Long userId;
}
