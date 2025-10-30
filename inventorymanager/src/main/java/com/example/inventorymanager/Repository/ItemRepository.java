package com.example.inventorymanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventorymanager.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
