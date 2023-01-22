package com.expensebe.expensebe;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document("expenses")
public class Expenses {
    String id;
    String title;
    String category;
    Integer amount;
    String date;
    String userName;
}
