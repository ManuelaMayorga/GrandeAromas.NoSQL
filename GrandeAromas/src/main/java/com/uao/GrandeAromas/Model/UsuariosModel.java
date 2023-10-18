package com.uao.GrandeAromas.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uao.GrandeAromas.Enums.RolEnum;
import com.uao.GrandeAromas.Model.ReviewsModel.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

List <User>
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
    public List<User> getNameUser() {
        return null;
    }
    public void setNameUser(List<User> nameUser2) {
    }
    public Object getId() {
        return null;
    }
    public void setId(Object id2) {
    }

    

}
