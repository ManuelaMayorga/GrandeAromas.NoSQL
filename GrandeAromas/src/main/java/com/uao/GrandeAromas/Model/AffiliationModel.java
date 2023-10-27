package com.uao.GrandeAromas.Model;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uao.GrandeAromas.Enums.StateEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document("Affiliation")
public class AffiliationModel {
    @Id
    private int id;
    private int userId;
    private int membershipId;
    private String email;
    private Date start_date;
    private Date finish_date;
    private StateEnum state;
}

