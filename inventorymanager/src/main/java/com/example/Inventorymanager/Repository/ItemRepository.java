package com.example.Inventorymanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Inventorymanager.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
