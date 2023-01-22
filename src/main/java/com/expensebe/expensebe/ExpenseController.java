package com.expensebe.expensebe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("Test Pass");
    }

    @GetMapping
    public ResponseEntity<List<Expenses>> getAllExpenses() {
        return ResponseEntity.ok(this.expenseService.getAllExpenses());
    }

    @PostMapping
    public ResponseEntity<Expenses> postExpense(@RequestBody Expenses expenses) {
        return ResponseEntity.ok(this.expenseService.saveExpense(expenses));
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable String id) {
        this.expenseService.deleteExpense(id);
    }
}
