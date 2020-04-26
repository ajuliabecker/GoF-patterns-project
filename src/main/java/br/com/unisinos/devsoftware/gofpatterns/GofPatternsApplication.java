package br.com.unisinos.devsoftware.gofpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GofPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GofPatternsApplication.class, args);
        UpdateCovidData updateCovidData = new UpdateCovidData();
        updateCovidData.upadte();
    }

}
