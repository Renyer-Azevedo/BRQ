package com.brq.santander.cadeiasocietaria.model;

import java.math.BigDecimal;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "cadeia_societaria")
public class CorporateChain {
    @PrimaryKeyColumn(ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    String penumper;
    @PrimaryKeyColumn(ordinal = 2,type = PrimaryKeyType.PARTITIONED)
    String penumero;
    String flagBf;
    String flagCompletude;
    String origem;
    String pecaradm;
    String pecarcon;
    @PrimaryKeyColumn(ordinal = 1,type = PrimaryKeyType.PARTITIONED)
    String pecodrel;
    java.sql.Timestamp pefecalt;
    java.sql.Timestamp pefecini;
    java.sql.Timestamp pefecter;
    java.sql.Timestamp pehstamp;
    String peindrep;
    BigDecimal penivel;
    String penomfan;
    String penomper;
    @PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.PARTITIONED)
    String penumpri;
    String penumrel;
    BigDecimal peporcom;
    BigDecimal peporpar;
    BigDecimal peporpri;
    BigDecimal peporrel;
    String petipdoc;
    String petipdocpri;
    String petippar;
    String petipper;
    String peusualt;
    String peusumod;
    BigDecimal porreltot;
    String relnumero;
}
