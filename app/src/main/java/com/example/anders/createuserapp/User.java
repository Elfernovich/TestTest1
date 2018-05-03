
package com.example.anders.createuserapp;
public class User {
    Boolean user_artwork1;
    Boolean user_artwork2;
    Boolean user_artwork3;
    String email;
    String firstName;
    String lastName;
    String memberNumber;
    int points;



    public User(){

    }

    public User(String firstName, String lastName, String memberNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
    }

    public User(String email, String firstName, String lastName, String memberNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
    }

    public User(String email, String firstName, String lastName, String memberNumber, int points, boolean user_artwork1, boolean user_artwork2, boolean user_artwork3) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
        this.points = points;
        this.user_artwork1 = user_artwork1;
        this.user_artwork2 = user_artwork2;
        this.user_artwork3 = user_artwork3;

    }



    public Boolean getUser_artwork1() {
        return user_artwork1;
    }

    public Boolean getUser_artwork2() {
        return user_artwork2;
    }

    public Boolean getUser_artwork3() {
        return user_artwork3;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    public void setUser_artwork3(Boolean user_artwork3) {
        this.user_artwork3 = user_artwork3;
    }

    public void setUser_artwork2(Boolean user_artwork2) {
        this.user_artwork2 = user_artwork2;
    }

    public void setUser_artwork1(Boolean user_artwork1) {
        this.user_artwork1 = user_artwork1;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


