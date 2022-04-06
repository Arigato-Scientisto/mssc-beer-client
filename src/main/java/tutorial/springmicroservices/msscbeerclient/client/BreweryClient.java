package tutorial.springmicroservices.msscbeerclient.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tutorial.springmicroservices.msscbeerclient.web.model.BeerDto;
import tutorial.springmicroservices.msscbeerclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

/**
 * created by Joseph Yacoub  on 04 Apr 2022
 */
@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreInvalidFields = false)
@Data
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apiHost + BEER_PATH_V1 + "/" + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apiHost + BEER_PATH_V1 + "/" + uuid.toString());
    }

    /*Methods for the Customer*/

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apiHost + getCUSTOMER_PATH_V1(), customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(apiHost + getCUSTOMER_PATH_V1() + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apiHost + getCUSTOMER_PATH_V1() + uuid);
    }
}
