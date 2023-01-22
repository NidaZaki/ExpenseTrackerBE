package com.expensebe.expensebe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@CrossOrigin(origins = "*")
public class CategoriesController {

    final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<Categories> getAllCategories(@PathVariable String userName) { //Send parameter, userName
        return ResponseEntity.ok(this.categoriesService.getCategoryList(userName));
    }

    @GetMapping("/getExpensesByCategory/{userName}")
    public ResponseEntity<List<Integer>> getExpensesByCategory(@PathVariable String userName , @RequestParam Integer month, @RequestParam Integer year){
        return ResponseEntity.ok(this.categoriesService.getExpensesByCategory(userName, month, year));
    }

//    @GetMapping("/getExpensesByCategory/{userName}")
//    public ResponseEntity<ExpenseCategory> getObjectByCategory(@PathVariable String userName){
//        return ResponseEntity.ok(this.categoriesService.getObjectByCategory(userName));
//    }

    @PostMapping
    public ResponseEntity<Categories> postCategories(@RequestBody Categories categories) {
        return ResponseEntity.ok(this.categoriesService.saveCategories(categories));
    }


}
