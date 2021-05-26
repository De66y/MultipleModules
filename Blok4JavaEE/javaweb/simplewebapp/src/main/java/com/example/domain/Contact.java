package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {

    private String firstname;
    private String lastname;
    private String email;

    public String getFullname() {
        return firstname + " " + lastname;
    }

}
