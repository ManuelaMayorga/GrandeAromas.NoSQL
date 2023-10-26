package com.uao.GrandeAromas.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Document("DetailShoppingCart")
public class DetailShoppingCartModel {
    @Id
    private String id;
    private int shoppingCartId;
    private int productId;
    private int quantity;
    
}
