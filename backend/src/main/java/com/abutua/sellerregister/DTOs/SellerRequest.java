package com.abutua.sellerregister.DTOs;

import com.abutua.sellerregister.entities.Seller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SellerRequest {

    @NotBlank(message = "A name must not be empty")
    @Size(min = 5, max = 255, message = "A name must be between 5 and 255 characters")
    private String name;

    @NotNull(message = "A salary must not be empty")
    @Min(value = 0, message = "A salary must be higher than zero.")
    private Double salary;

    @NotNull(message = "A bonus must not be empty")
    @Min(value = 0, message = "A bonus must be higher than 0%")
    @Max(value = 100, message = "A bonus must not be higher than 100%")
    private Double bonus;

    @NotBlank(message = "A gender must not be empty")
    @Pattern(regexp = "^(male|female)$", message = "A gender must be male or female")
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
