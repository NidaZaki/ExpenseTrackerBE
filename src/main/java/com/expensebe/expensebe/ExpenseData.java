package com.expensebe.expensebe;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseData {
    List<Expenses> expensesListChart;
    List<Expenses> expensesList;
}
