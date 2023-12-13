package com.example.PostalCodeValidator.Service;

import com.example.PostalCodeValidator.enums.CountryCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryValidatorService {

    @Value("${zippopotam.api.url}")
    private String zippopotamApiUrl;

    private  final RestTemplate restTemplate;

    public CountryValidatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isPostalCodeValid(String country, String postalCode) {
        try {
            // country = country.replace(" ", "_").toLowerCase();
            CountryCode countryCode = getCountryCode(country.replace(" ", "_").toLowerCase());

            String apiUrl = zippopotamApiUrl + countryCode.getCode() + "/" + postalCode.split(" ")[0];
            restTemplate.getForObject(apiUrl, String.class) ;
            return true;

        } catch (Exception e) {
            return  false;
        }
    }



    private static CountryCode getCountryCode(String countryName) {
        try {
            return CountryCode.valueOf(countryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown country: " + countryName);
        }
    }

}