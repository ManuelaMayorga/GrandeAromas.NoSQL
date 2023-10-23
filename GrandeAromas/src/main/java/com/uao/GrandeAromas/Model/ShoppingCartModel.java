package com.uao.GrandeAromas.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uao.GrandeAromas.Enums.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document("ShoppingCart")
public class ShoppingCartModel {
    @Id
    private String id;
    private int userId;
    private List<AddressInfo> addressInfo;
    private Date date;
    private double totalPrice;
    private OrderStatusEnum orderStatus;

}




