package com.abutua.sellerregister.DTOs;

import com.abutua.sellerregister.entities.Seller;

public class SellerRequest {

    private String name;
    private Double salary;
    private Double bonus;
    private String gender;

    // Empty Constructor
    public SellerRequest(){

    }

    // Constructor
    public SellerRequest(String name, Double salary, Double bonus, String gender) {
        this.name = name;
        this.salary = salary;
        this.bonus = bonus;
        this.gender = gender;
    }

    // Converts to SellerRequest to SellerEntity
    public Seller toSellerEntity(){
        return new Seller(name, salary, bonus, gender);
    }

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
