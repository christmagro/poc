package com.ballys.poc.dto;

import java.util.HashMap;
import lombok.Data;

@Data
public class FieldMethodDto {

  private long id;

  private String methodTitle;

  private HashMap<String, MethodFieldGeneric> methodFields;

}
