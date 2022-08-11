package com.example.crud.controllers;

import com.example.crud.model.product.Product;
import com.example.crud.model.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/products")
    public @ResponseBody Iterable<Product> get(){
        return productRepository.findAll();
    }

    @GetMapping(path = "/barCode/{barCode}")
    public @ResponseBody Optional<Product> get(@PathVariable String barCode){
        return this.productRepository.searchProductByBarCode(barCode);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Product> get(@PathVariable Long id){
        return productRepository.findById(id);
    }

    @PostMapping
    public @ResponseBody Product post(@Valid @RequestBody Product product){
        System.out.println(product);
        productRepository.save(product);
        return product;
    }

    @PutMapping(path = "/put/{barCode}")
    public @ResponseBody Product put(@Valid @RequestBody Product product, @PathVariable String barCode){
        Optional<Product> oldProduct = this.get(barCode);
        if (oldProduct.isPresent()){
            oldProduct.get().setPrice(product.getPrice());
            oldProduct.get().setName(product.getName());
            oldProduct.get().setPrice(product.getPrice());
            this.productRepository.save(oldProduct.get());
        }
        return oldProduct.get();
    }

    @DeleteMapping(path = "/remove/{id}")
    public @ResponseBody Optional<Product> delete(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) productRepository.delete(product.get());
        return product;
    }

    @DeleteMapping(path = "/remove/barCode/{barCode}")
    public @ResponseBody Optional<Product> delete(@PathVariable String barCode){
        Optional<Product> product = this.productRepository.searchProductByBarCode(barCode);
        if (product.isPresent()){
            this.productRepository.delete(product.get());
        }
        return product;
    }


}
