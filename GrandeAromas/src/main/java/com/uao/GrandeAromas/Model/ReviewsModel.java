package com.uao.GrandeAromas.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Document("Reviews")
public class ReviewsModel {
    @Id
    private int id;
    private int userId;
    private String userName;
    private int product_id;
    private String comment;
    private int calification;
    private Date date;
}
