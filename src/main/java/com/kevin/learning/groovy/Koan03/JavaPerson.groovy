package com.kevin.learning.groovy.Koan03

/**
 * Created by kaiwen on 2016/8/11.
 */
class JavaPerson {

    private String firstName;
    private String lastName;

    private final String ssn;

    JavaPerson(String firstName, String lastName, String ssn) {
        this.firstName = firstName
        this.lastName = lastName
        this.ssn = ssn
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

    String getSsn() {
        return ssn
    }
}
