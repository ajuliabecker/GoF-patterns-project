package br.com.unisinos.devsoftware.gofpatterns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class UpdateData {

    private static String REQUEST_URL = "https://pomber.github.io/covid19/timeseries.json";

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public Map<String, ResponseDto[]> upadte() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        String json = restTemplate.getForObject(REQUEST_URL, String.class);
        Map<String, ResponseDto[]> responseDto = null;
        try {
            responseDto = objectMapper.readValue(json, new TypeReference<Map<String, ResponseDto[]>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

}
