package com.uao.GrandeAromas.Domain;

import java.util.Date;
import java.util.List;
import java.util.Map; 
import com.uao.GrandeAromas.Enums.OrderStatusEnum;
import com.uao.GrandeAromas.Model.AddressInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private int id;
    private int userId;
    private List<AddressInfo> addressInfo;
    private Date date;
    private double totalPrice;
    private OrderStatusEnum orderStatus;
    private List<Map<String, Integer>> detalleVenta;
}


    