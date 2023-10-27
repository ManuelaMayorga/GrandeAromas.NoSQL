package com.uao.GrandeAromas.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Membership")
public class MembershipModel {
    @Id
    private int id;
    private String nombre;
    private String description;
    private double cost;
}
