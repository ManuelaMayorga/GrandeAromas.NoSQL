package com.uao.GrandeAromas.Domain;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Consulta2DTO {
    private int codigoVenta;
    private String nombreCliente;
    private double totalCompra;
    private List<Map<String, Integer>> detalleVenta;
    
}
