package com.example.employeeapi;

import org.json.JSONObject;

public class EmployeeData {

    private int employeeID;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String addressStreet;
    private String addressState;
    private String addressCity;
    private String addressZip;
    private String positionName;
    private String positionBaseSalary;
    private String hireDate;

    //Setter Method
    public void setData(JSONObject object){
        try {
            this.employeeID = object.getInt("EmployeeID");
            this.firstName = object.getString("FirstName");
            this.lastName = object.getString("LastName");
            this.phoneNum = object.getString("PhoneNum");
            this.addressStreet = object.getString("AddressStreet");
            this.addressState = object.getString("AddressState");
            this.addressCity = object.getString("AddressCity");
            this.addressZip = object.getString("AddressZip");
            this.positionName = object.getJSONObject("Position").getString("PositionName");
            this.positionBaseSalary = object.getJSONObject("Position").getString("PositionBaseSalary");
            this.hireDate = object.getString("HireDate");

        } catch(Exception e) {
            System.out.println("Logged from setData() in EmployeeData.java " + e);
        }
    }

    //Getter methods
    public int getEmployeeID(){
        return this.employeeID;
    }
    public String getFullName(){
        String fullName = this.firstName + " " + this.lastName;
        return fullName;
    }
    public String getPhoneNumber(){
        return this.phoneNum;
    }
    public String getAddress(){
        String address = this.addressStreet + ", " + this.addressCity + ", " + this.addressState + " " + this.addressZip;
        return address;
    }
    public String getPositionName(){
        return this.positionName;
    }
    public String getPositionBaseSalary(){
        return this.positionBaseSalary;
    }
    public String getHireDate(){
        return this.hireDate;
    }
}
