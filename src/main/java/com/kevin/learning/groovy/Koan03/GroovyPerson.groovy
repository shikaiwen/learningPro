package com.kevin.learning.groovy.Koan03

/**
 * Created by kaiwen on 2016/8/11.
 */
class GroovyPerson {

    String firstName;
    String lastName;
    final String ssn;

    GroovyPerson(String firstName, String lastName, String ssn) {
        this.firstName = firstName
        this.lastName = lastName
        this.ssn = ssn
    }
}
