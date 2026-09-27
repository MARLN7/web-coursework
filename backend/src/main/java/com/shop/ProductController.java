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
        products.add(new Product(1, "Смартфон Samsung Galaxy S26", 80354.0, 15, "Электроника", "6.3, AMOLED, 12/256 ГБ, камера 50 МП, Android 16", "active"));
        products.add(new Product(2, "Ноутбук ASUS VivoBook 16 Pro", 172618.0, 8, "Электроника", "16.0, OLED, Ryzen AI 9, 32 ГБ RAM, 1024 ГБ SSD, Windows 11", "active"));
        products.add(new Product(3, "Наушники Apple AirPods 5", 27188.0, 0, "Аудио", "Беспроводные, с активным шумоподавлением, до 30 ч работы", "sold_out"));
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
