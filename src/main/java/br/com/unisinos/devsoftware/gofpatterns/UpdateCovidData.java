package br.com.unisinos.devsoftware.gofpatterns;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class UpdateCovidData {

    private static String REQUEST_URL = "https://pomber.github.io/covid19/timeseries.json";

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public HashMap upadte() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        String json = restTemplate.getForObject(REQUEST_URL, String.class);

        HashMap responseDto = new HashMap();
        try {
            responseDto = objectMapper.readValue(json, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }
}
