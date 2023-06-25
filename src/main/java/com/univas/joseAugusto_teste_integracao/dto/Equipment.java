package com.univas.joseAugusto_teste_integracao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    private Long id;

    private String tag;

    private String name;

    private String provider;

    private Date nextMaitenanceDate;

    private Double weight;

    private boolean active;
}
