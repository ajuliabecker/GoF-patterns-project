package br.com.unisinos.devsoftware.gofpatterns.singleton;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class UpdateCovidDataSingleton {

    private static UpdateCovidDataSingleton updateCovidInstance;
    private static String REQUEST_URL = "https://pomber.github.io/covid19/timeseries.json";

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    private UpdateCovidDataSingleton() {
    }

    public static synchronized UpdateCovidDataSingleton getInstance() {
        if (updateCovidInstance == null) {
            return new UpdateCovidDataSingleton();
        } else {
            return updateCovidInstance;
        }
    }

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
