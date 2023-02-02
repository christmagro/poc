package com.ballys.poc.controllers;

import com.ballys.poc.dto.FieldMethodDto;
import com.ballys.poc.dto.MethodFieldGeneric;
import com.ballys.poc.model.MethodFields;
import com.ballys.poc.repository.MethodFieldsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

  private final ObjectMapper objectMapper;

  BiPredicate<HashMap<String, MethodFieldGeneric>, String> checkCurrency = ((configValues, value) ->
      configValues.entrySet().stream()
          .filter(f -> f.getKey().equalsIgnoreCase("currencies"))
          .anyMatch(t -> t.getValue().getValues().contains(value)));

  BiPredicate<HashMap<String, MethodFieldGeneric>, String> checkCountry = ((configValues, value) ->
      configValues.entrySet().stream()
          .filter(f -> f.getKey().equalsIgnoreCase("countries"))
          .anyMatch(t -> t.getValue().getValues().contains(value)));

  private final MethodFieldsRepository methodFieldsRepository;

  @PostMapping("/")
  private void addConfiguration(@RequestParam(defaultValue = "EUR") List<String> currencies, @RequestParam(defaultValue = "MT") List<String> countries, @RequestParam(defaultValue = "Generic Config") String methodTitle) {

    MethodFields fields = new MethodFields();

    Map<String, Object> methodsFields = new HashMap<>();

    MethodFieldGeneric methodFieldCountry = new MethodFieldGeneric();
    methodFieldCountry.setValues(countries);
    methodFieldCountry.setInclude(Boolean.TRUE);

    MethodFieldGeneric methodFieldCurrency = new MethodFieldGeneric();
    methodFieldCurrency.setValues(currencies);

    methodsFields.put("Countries", methodFieldCountry);
    methodsFields.put("Currencies", methodFieldCurrency);

    fields.setFieldAttributes(methodsFields);
    fields.setMethodTitle(methodTitle);
    methodFieldsRepository.save(fields);

  }


  @GetMapping("/")
  private List<FieldMethodDto> getAllowed(@RequestParam(defaultValue = "EUR") String currency, @RequestParam(defaultValue = "MT") String country) {
    return methodFieldsRepository.findAll().stream()
        .map(t -> {
          FieldMethodDto fieldMethodDto = new FieldMethodDto();
          fieldMethodDto.setMethodTitle(t.getMethodTitle());

          HashMap<String, MethodFieldGeneric> methodFields = new HashMap<>();

          t.getFieldAttributes().forEach((key, value) -> methodFields.put(key,
              objectMapper.convertValue(value, MethodFieldGeneric.class)));

          fieldMethodDto.setMethodFields(methodFields);
          return fieldMethodDto;

        }).filter(t -> checkCountry.test(t.getMethodFields(), country))
        .filter(t -> checkCurrency.test(t.getMethodFields(), currency))
        .collect(Collectors.toList());

  }
}
