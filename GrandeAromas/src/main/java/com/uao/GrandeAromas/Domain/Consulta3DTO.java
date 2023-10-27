package com.uao.GrandeAromas.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Consulta3DTO {

    private String category;
    private String productName;
    private int quantity;
    
}
