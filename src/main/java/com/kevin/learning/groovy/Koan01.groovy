package com.kevin.learning.groovy
/**
 * Created by kaiwen on 2016/8/10.
 */

class Koan01 extends GroovyTestCase{

    void test01_AsserttionsAndSomeSyntax() {

        boolean assertion = false
        def hello = "Hola"

        hello= "hello"
        assertion = true
        assert assertion, 'assign "true" to the "assertion" variable to proceed'
        assert hello == "hello , 'modify "


        println('ok')


    }


    void test02_gstrings() {
        //Groovy allows you to use either regular quotes(') or double-quote(") for string declarations.
        // The difference is that double-quotes create  a GString, which is a super-powered String.
        // For docs about GStrings, see http://docs.groovy-lang.org/latest/html/documentation/index.html#all-strings

        // GStrings allow you to use the ${} syntax within them . The ${} and contain any valid Groovy expression .
        def name = 'George'
        def greeting = "Hello ${name}, how are you ?"
        def math = "The result of 4 + 4 is ${4 + 4}"

        // Create the target string with the ${} mechanism. Remember that ${} can contain emthod calls to !
        String result
        // -------- START EDITING HERE ---------------
        result = " The size of the string '${greeting}' is ${greeting.size()}"
        print result
        assert result == "The size of the string 'Hello George,how are you?' is 26"

    }


    void test03_MapsInGroovy() {
        // Maps area also special citizens in Groovyland
        // Docs can be found here: http://groovy-lang.org/groovy-dev-kit.html#_working_with_collections
        def map = [right: 'decrecha', left: 'izquierda']

        // Concatenate the two values fo 'right' and 'left' into results to proceed using Groovy syntax
        def result
        result = map['right'] + map['left']
        assert result.toCharArray().size() == 17


    }

    void test04_Lists() {
        // In Java , list creation can be somewhat cumbersome;
        List<String> javaList = new ArrayList<String>();
        javaList.add("King")
        javaList.add("Queen")
        javaList.add("Prince")


        // In Groovy , this is simplified to :
        // (See http://groovy-lang.org/groovy-dev-kit.html#_working_with_collections
        // and http://docs.groovy-lang-org/latest/html/groovy-jdk-java/util/List.html)
        def groovyList = ['King', 'Prince']

        // Add the missing item to the Groovy list . Pay attenstion to the order of the items
        // Hint: you can use either Java's add(int,String) or Groovy's plus() method.
        // ------------START EDITING HERE ---------------
        groovyList = groovyList.plus(1, 'Queen')
        // ----------- STOP EDITING HERE ----------
        assert groovyList == javaList

    }

    void test05_ElvisAndSafeNavigation() {
        // Preparation code for the examples that follow. We'll get to this code in laster Koans.
        User player = new User('Ronaldo','Nazario de Lima','ron',null)
        UserService userServiceWithUserLoggedIn = [getLoggedInUser:{player}] as UserService
        UserService userServiceWithoutLoggedInUser = [getLoggedInUser: {null}] as UserService

        // Groovy introduces two convenient operations for dealing with nulls: elvis(?:) and safe navigation (?.)
        // Read all about it at http://docs.groovy-lang.org/lastest/html/documentation/index.html#groovy-operators

        // Assume we have a User object that may or may not contain a first name and an address.
        // In Java, we could end up with the following code:

        String firstName = player.getFirstName();
        String javaDispalyName = firstName == null ? player.getUsername() : firstName;
        String javaCity = "";
        if(player.getAddress() != null && player.getAddress().getCity() != null) {
            // Be careful of NullPointerExceptions
            javaCity = player.getAddress().getCity();
        }

        // With Groovy's new operators, this becomes much simpler:
        def groovyDisplayName = player.firstName ?: player.username
        def groovyCity = player?.address?.city

        // Using your newly acquired knowledge , fix the createMessageForUser method below
        // so that anonymous users get 'Hello Anonymous!' and logged in users get 'Hello <first name>'
        // you shoud use userService.getLoggedInUser() as well.
        assert createMessageForUser(userServiceWithUserLoggedIn) == 'Hello Ronaldo!'
        assert createMessageForUser(userServiceWithoutLoggedInUser) == 'Hello Anonymous!'

    }

    private String createMessageForUser(UserService userService) {
        def message
        message = "Hello ${userService.loggedInUser?.firstName ?: 'Anonymous'}!"

        // Note how Groovy doesn't require the 'return' keyword! It will simply return the last expression
        message
    }


}
