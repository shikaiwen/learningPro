package com.kevin.learning.groovy

/**
 * Created by kaiwen on 2016/8/10.
 */
class User {
    private String firstName;
    private String lastName;
    private String username;
    private  Address address;

    User(String firstName, String lastName, String username, String address) {
        this.firstName = firstName
        this.lastName = lastName
        this.username = username
        this.address = address
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    Address getAddress() {
        return address
    }

    void setAddress(Address address) {
        this.address = address
    }
}
