package com.ballys.poc.dto;

import java.util.List;
import lombok.Data;

@Data
public class MethodFieldCountry {
  private boolean include;
  private List<String> countries;

}
