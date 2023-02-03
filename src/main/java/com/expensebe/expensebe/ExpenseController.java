package com.expensebe.expensebe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<ExpenseData> getExpenseData(@RequestParam(required = false) String month,
                                                      @RequestParam(required = false) String year,
                                                      @RequestParam(required = false) String category,
                                                      @RequestParam(required = false) String userName) {
        return ResponseEntity.ok(this.expenseService.getExpenseData(month, year, category, userName));
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
