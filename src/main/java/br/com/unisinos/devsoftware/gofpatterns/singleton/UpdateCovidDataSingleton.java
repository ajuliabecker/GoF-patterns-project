package br.com.unisinos.devsoftware.gofpatterns.singleton;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static java.util.Objects.isNull;

public class UpdateCovidDataSingleton {

    private static UpdateCovidDataSingleton updateCovidInstance;
    private static final String REQUEST_URL = "https://pomber.github.io/covid19/timeseries.json";

    private UpdateCovidDataSingleton() {
    }

    public static synchronized UpdateCovidDataSingleton getInstance() {
        if (isNull(updateCovidInstance)) {
            updateCovidInstance = new UpdateCovidDataSingleton();
        }
        return updateCovidInstance;
    }

    public HashMap<String, List<ResponseDto>> update() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
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
