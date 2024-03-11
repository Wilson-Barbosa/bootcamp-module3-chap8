package com.abutua.sellerregister.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abutua.sellerregister.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    
}
