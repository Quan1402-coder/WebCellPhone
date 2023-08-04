package com.charles.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Brand extends BaseDomain {
    private String name;
    private String description;
}
