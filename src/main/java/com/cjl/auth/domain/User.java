package com.cjl.auth.domain;

import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;

    public boolean matchesPassword(String aPassword){
        return this.password.equals(aPassword);
    }
}
