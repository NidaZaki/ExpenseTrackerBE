package com.expensebe.expensebe;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document("expense-categories")
public class ExpenseCategory {
    List<String> categoryList;
    List<Integer> expenseByCategory;
}
