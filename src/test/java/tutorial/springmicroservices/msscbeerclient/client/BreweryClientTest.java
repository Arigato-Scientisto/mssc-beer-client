package tutorial.springmicroservices.msscbeerclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tutorial.springmicroservices.msscbeerclient.web.model.BeerDto;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by Joseph Yacoub  on 04 Apr 2022
 */
@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() {
        BeerDto beerDto = BeerDto.builder()
                .beerName("Eichbaum")
                .beerStyle("Pils")
                .build();

        URI uri = client.saveNewBeer(beerDto);

        assertNotNull(uri);
    }

    @Test
    void updateBeer() {
        BeerDto beerDto = BeerDto.builder()
                .beerName("Eichbaum")
                .beerStyle("Pils")
                .build();
        client.updateBeer(UUID.randomUUID(), beerDto);
    }
    @Test
    void deleteBeer(){
        client.deleteBeer(UUID.randomUUID());
    }
}