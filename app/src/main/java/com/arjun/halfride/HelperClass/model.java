package com.arjun.halfride.HelperClass;

public class model {

    String name, location, organisation, description, userId, number, email;

    public model(){

    }

    public model(String number, String email) {
        this.number = number;
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public model(String name, String location, String organisation, String description, String userId) {
        this.name = name;
        this.location = location;
        this.organisation = organisation;
        this.description = description;
        this.userId = userId;
    }
}

