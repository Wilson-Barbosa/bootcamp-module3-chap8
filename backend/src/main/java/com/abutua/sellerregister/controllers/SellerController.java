package com.abutua.sellerregister.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.sellerregister.DTOs.SellerResponse;
import com.abutua.sellerregister.services.SellerService;

@RestController
@RequestMapping("sellers")
public class SellerController {
    
    @Autowired
    SellerService sellerService;   // Injecting the SellerService
    
    // Endpoint to access all sellers
    @GetMapping()
    public ResponseEntity<List<SellerResponse>> getAllSellers(){
        // Returns a ResponseEntity containing a List of SellerResponse
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    // Endpoint to a single seller
    @GetMapping("{id}")
    public ResponseEntity<SellerResponse> getSellerById(@PathVariable long id){
        // Returns a ResponseEntity for a single seller
        return ResponseEntity.ok(sellerService.getById(id));
    }

    // Endpoint to delete a seller
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSellerById(@PathVariable long id){
        // Calls the method that deletes a single seller
        sellerService.deleteById(id);

        // Returns the response with a status code and an empty body
        return ResponseEntity.noContent().build();
    }


}
