package com.shop;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final List<Product> products = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(4);

    public ProductController() {
        products.add(new Product(1, "Смартфон Samsung Galaxy S24", 79990.0, 15, "Электроника", "6.2\" AMOLED, 8/256 ГБ, камера 50 МП, Android 14", "active"));
        products.add(new Product(2, "Ноутбук ASUS VivoBook 15", 54990.0, 8, "Электроника", "15.6\" FHD, Ryzen 5, 16 ГБ RAM, 512 ГБ SSD, Windows 11", "active"));
        products.add(new Product(3, "Наушники Sony WH-1000XM5", 24990.0, 0, "Аудио", "Беспроводные, активное шумоподавление, до 30 ч работы", "sold_out"));
    }

    @GetMapping
    public List<Product> getAll() {
        return products;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        product.setId(idCounter.getAndIncrement());


        if (product.getQuantity() <= 0) {
            product.setStatus("sold_out");
        } else {
            product.setStatus("active");
        }

        products.add(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
    }

    @PatchMapping("/{id}/archive")
    public Product archive(@PathVariable int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setStatus("archived");
                return p;
            }
        }
        return null;
    }
}
