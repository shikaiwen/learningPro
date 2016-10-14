package com.kevin.learning.groovy

/**
 * Created by kaiwen on 2016/8/10.
 */
class Address {

    private String street;
    private String city;
    private String zipCode;

    String getStreet() {
        return street
    }

    void setStreet(String street) {
        this.street = street
    }

    String getCity() {
        return city
    }

    void setCity(String city) {
        this.city = city
    }

    String getZipCode() {
        return zipCode
    }

    void setZipCode(String zipCode) {
        this.zipCode = zipCode
    }
}
