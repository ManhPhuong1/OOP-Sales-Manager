package com.sapo.salemanagement.repositories.shop;

import com.sapo.salemanagement.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Shop findById(int id);
}
