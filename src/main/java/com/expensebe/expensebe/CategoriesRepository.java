package com.expensebe.expensebe;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoriesRepository extends MongoRepository<Categories, String> {
    List<Categories> findAll();
}
