package com.bridgelabz;

import java.util.Objects;


public class Contact {

    private String FirstName;
    private String LastName;
    private String City;
    private String Address;
    private String State;
    private String Zip;
    private String PhoneNumber;
    private String Email;

    public Contact() {
    }

    public JSONObject getContactJSON() {
        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("firstName", getFirstName());
        jsonPerson.put("lastName", getLastName());
        jsonPerson.put("city", getCity());
        jsonPerson.put("address", getAddress());
        jsonPerson.put("state", getState());
        jsonPerson.put("zip", getZip());
        jsonPerson.put("phonenumber", getPhoneNumber());
        jsonPerson.put("email", getEmail());
        JSONObject jsonPersonObject = new JSONObject();
        jsonPersonObject.put("person", jsonPerson);
        return jsonPersonObject;
    }
    public Contact(String firstName, String lastName, String city, String address, String state, String zip, String phoneNumber, String email) {
        FirstName = firstName;
        LastName = lastName;
        City = city;
        Address = address;
        State = state;
        Zip = zip;
        PhoneNumber = phoneNumber;
        Email = email;
    }
    String[] getContactStrings() {
        return new String[] {getFirstName(), getLastName(), getCity(), getAddress(), getState(), getZip(), getPhoneNumber() + "", getEmail() + ""};
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to AddressBook Program");
    }



    @Override
    public int hashCode() {
        return Objects.hash(getFirstName());
    }
    @Override
    public String toString() {
        return "Contact{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", City='" + City + '\'' +
                ", Address='" + Address + '\'' +
                ", State='" + State + '\'' +
                ", Zip='" + Zip + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
