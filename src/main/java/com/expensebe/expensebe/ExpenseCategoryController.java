package com.expensebe.expensebe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/expense-categories")
@CrossOrigin(origins = "*")
public class ExpenseCategoryController {

    final ExpenseCategoryService expenseCategoryService;


    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ExpenseCategory> getExpensesByCategory(@PathVariable String userName, @RequestParam Integer month, @RequestParam Integer year){
        return ResponseEntity.ok(this.expenseCategoryService.getExpensesByCategory(userName, month, year));
    }
}
