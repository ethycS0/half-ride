package com.arjun.halfride.HelperClass;

public class requests {


    String from, name, number, email, location, organisation ;
    public requests(){

    }

    public requests(String from, String name , String number, String email, String location, String organisation) {
        this.from = from;
        this.name = name;
        this.number = number;
        this.email = email;
        this.location = location;
        this.organisation = organisation;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
