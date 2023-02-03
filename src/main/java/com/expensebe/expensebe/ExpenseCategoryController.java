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
    public ResponseEntity<ExpenseCategory> getExpensesByCategory(@PathVariable String userName, @RequestParam (required = false) String month, @RequestParam (required = false)String year){
        return ResponseEntity.ok(this.expenseCategoryService.getExpensesByCategory(userName, month, year));
    }
}
