package com.brq.santander.cadeiasocietaria.model;

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
  @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private String penumper;
  @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED)
  private Double penivel;
  @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.PARTITIONED)
  private String penumrel;
  @PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.PARTITIONED)
  private String pecodrel;
  @PrimaryKeyColumn(ordinal = 4, type = PrimaryKeyType.PARTITIONED)
  private String petipper;
  @PrimaryKeyColumn(ordinal = 5, type = PrimaryKeyType.PARTITIONED)
  private String pehstamp;
  @PrimaryKeyColumn(ordinal = 6, type = PrimaryKeyType.PARTITIONED)
  private String penumpri;
  private String flagBf;
  private String flagCompletude;
  private String origem;
  private String pecaradm;
  private String pecarcon;
  private String pefecalt;
  private String pefecini;
  private String pefecter;
  private String peindrep;
  private String penomper;
  private String penomrel;
  private String penumero;
  private Double peporcom;
  private Double peporpar;
  private Double peporpri;
  private Double peporrel;
  private String petipdoc;
  private String petippar;
  private String peusualt;
  private String peusumod;
  private Double porreltot;
  private String relnumero;
  private String reltipdoc;
}
