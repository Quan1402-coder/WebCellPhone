package com.charles.website.model.response;

import com.charles.website.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AddressResponse {
    private Long id;
    private String coutry;
    private String city;
    private String district;
    private String streeet;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.coutry = address.getCoutry();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.streeet = address.getStreet();
    }
}
