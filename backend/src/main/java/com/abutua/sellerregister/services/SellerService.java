package com.abutua.sellerregister.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abutua.sellerregister.DTOs.SellerRequest;
import com.abutua.sellerregister.DTOs.SellerResponse;
import com.abutua.sellerregister.entities.Seller;
import com.abutua.sellerregister.repositories.SellerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository; // Injecting the repository

    // Gets a seller by its ID
    public SellerResponse getById(long id) {
        try {
            // Gets a reference and converts from SellerEntity to SellerResponse
            return sellerRepository.getReferenceById(id).toSellerResponse();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Could not find a seller with this id");
        }
    }

    // Get all sellers
    public List<SellerResponse> getAllSellers() {

        // Returns a List<Seller> that needs to be converted to List<SellerResponse>
        return sellerRepository.findAll().stream().map(s -> s.toSellerResponse()).collect(Collectors.toList());
    }

    // Deletes a seller by its ID
    public void deleteById(long id) {

        // Checks if the entity exists then it deletes, if not an error is thrown
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There is no seller with this ID");
        }
    }

    // Updates a seller by its ID
    public void update(SellerRequest seller, long id) {

        if (sellerRepository.existsById(id)) {
            // Checks if the seller exists
            Seller sellerSearched = sellerRepository.getReferenceById(id);

            // Creating a new SellerEntity via Constructor
            // with the information provided by the arguments
            Seller newSeller = new Seller(
                    sellerSearched.getId(),
                    seller.getName(),
                    seller.getSalary(),
                    seller.getBonus(),
                    seller.getGender());

            // Saves the new seller
            sellerRepository.save(newSeller);
        } else {
            throw new EntityNotFoundException("There is no seller with this ID");
        }

    }

    // Saves a new seller
    public SellerResponse save(SellerRequest seller) {

        // Converts from SellerRequest to SellerEntity
        Seller newSeller = seller.toSellerEntity();

        // Saves the new SellerEntity with repository
        sellerRepository.save(newSeller);

        // Returns the seller as SellerResponse
        return newSeller.toSellerResponse();
        
    }

}
