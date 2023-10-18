package com.uao.GrandeAromas.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.uao.GrandeAromas.Enums.CategoryEnum;
import com.uao.GrandeAromas.Enums.UnitEnum;

@Data 
@AllArgsConstructor
@NoArgsConstructor

@Document("Products")
public class ProductsModel {
    @Id
    private int id;
    private String productName;
    private CategoryEnum category;
    private String description;
    private int quantity;
    private UnitEnum unit;
    private double price;

}
