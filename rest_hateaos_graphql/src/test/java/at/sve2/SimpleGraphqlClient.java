package at.sve2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SimpleGraphqlClient {

    private final String queryString = """
            {
              authors {
                name
                posts {
                  title
                }
              }
            }
            """;

    @Test
    public void queryAuthors() {
        var url = "http://localhost:8080/graphql";

        var restTemplate = new RestTemplate();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", queryString);

        var requestEntity = new HttpEntity<>(requestBody, headers);
        var response = restTemplate.postForEntity(url, requestEntity, String.class);

        System.out.println("done : " + response.getBody());

    }



}
