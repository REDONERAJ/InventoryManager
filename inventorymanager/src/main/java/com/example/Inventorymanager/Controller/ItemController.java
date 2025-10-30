package com.example.Inventorymanager.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.Inventorymanager.Entity.Item;
import com.example.Inventorymanager.Service.ItemService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> getAll() {
        return service.getAllItems();
    }

    @PostMapping
    public Item add(@RequestBody Item item) {
        return service.addItem(item);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteItem(id);
    }

    @PutMapping("/{id}/sell/{qty}")
    public Item sell(@PathVariable Long id, @PathVariable int qty) {
        return service.sellItem(id, qty);
    }
}
