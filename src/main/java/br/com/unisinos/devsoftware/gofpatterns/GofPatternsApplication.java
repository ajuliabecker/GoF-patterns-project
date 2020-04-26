package br.com.unisinos.devsoftware.gofpatterns;

import br.com.unisinos.devsoftware.gofpatterns.service.CoronaVirusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class GofPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GofPatternsApplication.class, args);
        UpdateCovidData covidData = new UpdateCovidData();
        HashMap<String, List<ResponseDto>> updatedCovidData = covidData.update();

        CoronaVirusService service = new CoronaVirusService();
        service.getHigherNumberOfCasesOnOneDay(updatedCovidData);
    }

}
