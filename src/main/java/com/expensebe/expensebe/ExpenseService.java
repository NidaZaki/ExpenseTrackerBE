package com.expensebe.expensebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExpenseService {

    @Autowired
    final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expenses> getAllExpenses() {
        return this.expenseRepository.findAll();
    }

    public Expenses saveExpense(Expenses expenses) {
        return this.expenseRepository.save(expenses);
    }

    public void deleteExpense(String id) {
        List<Expenses> expensesList = this.getAllExpenses();
        Expenses expense = expensesList.stream().filter(item -> Objects.equals(item.getId(), id)).findFirst().get();
        this.expenseRepository.delete(expense);
    }
}
