package com.example.PostalCodeValidator.Controller;


import com.example.PostalCodeValidator.Service.CountryValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    private final CountryValidatorService validatorService;

    public ApiController(CountryValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    @GetMapping("/validate-postal-code")
    public ResponseEntity<Map<String, Object>> validatePostalCode(
            @RequestParam String countryName,
            @RequestParam String postalCode) {

        try {
            boolean isValid = validatorService.isPostalCodeValid(countryName, postalCode);
            Map<String, Object> response = new HashMap<>();
            response.put("valid", isValid);
            response.put("message", isValid ? "Postal code is valid for the country." : "Postal code is not valid for the country.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while validating postal code.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

