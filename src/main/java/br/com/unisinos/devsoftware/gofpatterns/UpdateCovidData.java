package br.com.unisinos.devsoftware.gofpatterns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Component
public class UpdateCovidData {

    private static String REQUEST_URL = "https://pomber.github.io/covid19/timeseries.json";

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public HashMap<String, List<ResponseDto>> update() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        String json = restTemplate.getForObject(REQUEST_URL, String.class);

        HashMap<String, List<ResponseDto>> responseDto = new HashMap<>();

        try {
            responseDto = objectMapper.readValue(json, new TypeReference<HashMap<String, List<ResponseDto>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }
}
