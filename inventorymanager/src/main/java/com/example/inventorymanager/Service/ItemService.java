package com.example.inventorymanager.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.inventorymanager.Entity.Item;
import com.example.inventorymanager.Repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public List<Item> getAllItems() {
        return repo.findAll();
    }

    public Item addItem(Item item) {
        return repo.save(item);
    }

    public Item updateItem(Long id, Item updated) {
        return repo.findById(id)
                .map(item -> {
                    item.setName(updated.getName());
                    item.setPrice(updated.getPrice());
                    item.setQuantity(updated.getQuantity());
                    item.setSupplier(updated.getSupplier());
                    return repo.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public void deleteItem(Long id) {
        repo.deleteById(id);
    }

    public Item sellItem(Long id, int qty) {
        Item item = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        if (item.getQuantity() < qty)
            throw new RuntimeException("Not enough stock");
        item.setQuantity(item.getQuantity() - qty);
        return repo.save(item);
    }
}
