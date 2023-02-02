package com.ballys.poc.dto;

import java.util.List;
import lombok.Data;

@Data
public class MethodFieldCurrency {
  private boolean include;
  private List<String> currencies;
}
