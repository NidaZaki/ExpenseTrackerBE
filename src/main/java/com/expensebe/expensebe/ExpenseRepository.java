package com.expensebe.expensebe;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expenses, String> {
      List<Expenses> findAll();
}
