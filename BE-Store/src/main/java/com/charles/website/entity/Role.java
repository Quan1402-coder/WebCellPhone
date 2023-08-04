package com.charles.website.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Data
public class Role extends BaseDomain {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
    private String description;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }
}
