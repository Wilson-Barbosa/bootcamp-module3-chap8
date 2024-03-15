package com.abutua.sellerregister.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.sellerregister.DTOs.SellerRequest;
import com.abutua.sellerregister.DTOs.SellerResponse;
import com.abutua.sellerregister.services.SellerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin
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

        // Returns an empty ResponseEntity with its proper status code
        return ResponseEntity.noContent().build();
    }

    // Endpoint to uptade an existing seller
    @PutMapping("{id}")
    public ResponseEntity<Void> updateSeller(@RequestBody SellerRequest seller, @PathVariable long id){
        
        // Calls the update method
        sellerService.update(seller, id);

        // Returns an empty ResponseEntity with its proper status code
        return ResponseEntity.noContent().build();
    }

    // Endpoint to save a new seller
    @PostMapping()
    public ResponseEntity<SellerResponse> saveSeller(@Validated @RequestBody SellerRequest seller){
        
        // Calls the save method
        sellerService.save(seller);

        // Converts from SellerRequest to SellerEntity then to SellerResponse
        SellerResponse sellerResponse = seller.toSellerEntity().toSellerResponse();

        // Building the URL location
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(sellerResponse.getId())
            .toUri();

        // Returns a ResponseEntity with the new resource and its location (URL)
        return ResponseEntity.created(location).body(sellerResponse);
        //TODO when a seller retuns its ID is null, maybe check this later
    }
    

}
