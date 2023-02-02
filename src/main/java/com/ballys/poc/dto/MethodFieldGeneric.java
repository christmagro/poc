package com.ballys.poc.dto;

import java.util.List;
import lombok.Data;

@Data
public class MethodFieldGeneric {
  private boolean include;
  private List<String> values;

}
