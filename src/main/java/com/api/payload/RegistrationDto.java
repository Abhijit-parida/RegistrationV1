package com.api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {

    private String name;

    private String email;

    private String mobile;
}