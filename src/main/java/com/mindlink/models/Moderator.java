package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Moderator {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
