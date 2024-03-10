package com.abutua.sellerregister.DTOs;

public class SellerResponse {

    private Long id;
    private String name;
    private Double salary;
    private Double bonus;
    private String gender;
    
    // Empty Constructor
    public SellerResponse(){

    }

    // Constructor
    public SellerResponse(Long id, String name, Double salary, Double bonus, String gender) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.bonus = bonus;
        this.gender = gender;
    }

    // Setters and Getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
