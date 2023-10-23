package com.uao.GrandeAromas.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressInfo {
    private String address;
    private String neighborhood;
    private String city;
    private String country;
    private String department;
    private int zipCoder;
    private String contactTel;
}

