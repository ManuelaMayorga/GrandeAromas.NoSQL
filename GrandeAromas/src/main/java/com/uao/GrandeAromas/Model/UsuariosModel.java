package com.uao.GrandeAromas.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uao.GrandeAromas.Enums.RolEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document("User")
public class UsuariosModel {
    @Id
    private int id;
    private String nameUser;
    private String email;
    private String password;
    private RolEnum rol;
    private int Affiliation_id;

    

}
