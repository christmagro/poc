package com.ballys.poc.model;

import java.util.Map;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="MethodVisibility")
@Data
public class MethodFields {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private int siteId;

  private String methodTitle;


  @Convert(converter = HashMapConverter.class)
  private Map<String, Object> fieldAttributes;

}
