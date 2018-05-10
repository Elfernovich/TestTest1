
package com.example.anders.createuserapp;
public class User {
    Boolean artwork1;
    Boolean artwork2;
    Boolean artwork3;
    Boolean artwork4;
    Boolean artwork5;
    String email;
    String firstName;
    String lastName;
    String memberNumber;
    int points;
    int points_handler = 10000;


    public User() {

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

    public User(String email, String firstName, String lastName, String memberNumber, int points, int point_handler, boolean artwork1, boolean artwork2,
                boolean artwork3, boolean artwork4, boolean artwork5) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
        this.points = points;
        this.points_handler = points_handler;
        this.artwork1 = artwork1;
        this.artwork2 = artwork2;
        this.artwork3 = artwork3;
        this.artwork4 = artwork4;
        this.artwork5 = artwork5;

    }

    public int getPoints_handler() { return points_handler; }

    public void setPoints_handler(int points_handler) { this.points_handler = points_handler; }

    public Boolean getArtwork1() {
        return artwork1;
    }

    public void setArtwork1(Boolean artwork1) {
        this.artwork1 = artwork1;
    }

    public Boolean getArtwork2() {
        return artwork2;
    }

    public void setArtwork2(Boolean artwork2) {
        this.artwork2 = artwork2;
    }

    public Boolean getArtwork3() {
        return artwork3;
    }

    public void setArtwork3(Boolean artwork3) {
        this.artwork3 = artwork3;
    }

    public Boolean getArtwork5() {
        return artwork5;
    }

    public void setArtwork5(Boolean artwork5) {
        this.artwork5 = artwork5;
    }

    public Boolean getArtwork4() {
        return artwork4;
    }

    public void setArtwork4(Boolean artwork4) {
        this.artwork4 = artwork4;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}








