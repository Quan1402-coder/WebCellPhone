package com.charles.website.model.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String coutry;
    private String city;
    private String district;
    private String street;
}
