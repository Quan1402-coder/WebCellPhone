package com.charles.website.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseDomain {
    private String coutry;
    private String city;
    private String district;
    private String street;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
