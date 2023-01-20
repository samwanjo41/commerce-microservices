package com.samwanjohi.productservice.repository;

import com.samwanjohi.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
