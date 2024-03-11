package com.abutua.sellerregister.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abutua.sellerregister.DTOs.SellerResponse;
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


}
