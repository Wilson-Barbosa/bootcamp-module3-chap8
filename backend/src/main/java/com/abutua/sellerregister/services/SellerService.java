package com.abutua.sellerregister.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abutua.sellerregister.DTOs.SellerRequest;
import com.abutua.sellerregister.DTOs.SellerResponse;
import com.abutua.sellerregister.entities.Seller;
import com.abutua.sellerregister.repositories.SellerRepository;

@Service
public class SellerService {
    
    @Autowired
    SellerRepository sellerRepository; // Injecting the repository

    // Gets a seller by its ID
    public SellerResponse getById(long id){

        // Gets a reference and converts from SellerEntity to SellerResponse
        return sellerRepository.getReferenceById(id).toSellerResponse();
    }

    // Get all sellers
    public List<SellerResponse> getAllSellers(){

        // Returns a List<Seller> that needs to be converted to List<SellerResponse>
        return sellerRepository.findAll().stream().map(s -> s.toSellerResponse()).collect(Collectors.toList());
    }
    
    // Deletes a seller by its ID
    public void deleteById(long id){
        sellerRepository.deleteById(id);
    }

    // Updates a seller by its ID
    public void update(SellerRequest seller, long id){

        // Checks if the seller exists
        Seller sellerSearched = sellerRepository.getReferenceById(id);

        // Creating a new SellerEntity via Constructor
        // with the information provided on arguments 
        Seller newSeller = new Seller(sellerSearched.getId(), seller.getName(), seller.getSalary(), seller.getBonus(), seller.getGender());

        // Saves the new seller 
        sellerRepository.save(newSeller);

    }

    // Saves a new seller
    public SellerResponse save(SellerRequest seller){

        // Converts from SellerRequest to SellerEntity
        Seller newSeller = seller.toSellerEntity();
        
        // Saves the new SellerEntity with repository
        sellerRepository.save(newSeller);

        // Returns the seller as SellerResponse
        return newSeller.toSellerResponse();
    }


}
