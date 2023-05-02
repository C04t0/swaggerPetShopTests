package be.syntra.petstore;

import be.syntra.petstore.domain.ApiResponse;
import be.syntra.petstore.domain.Pet;
import be.syntra.petstore.domain.PetStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PetTest {

    private RestTemplate restTemplate = new RestTemplate();

    public static String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void addPetTest() {
        Pet pet  = new Pet();
        pet.setId(1);
        pet.setName("Bobbie");
        pet.setStatus(PetStatus.AVAILABLE);

        HttpHeaders headers = new HttpHeaders();
        headers.set("location", BASE_URL + "/pet/{petId}");
        HttpEntity<Pet> request = new HttpEntity<>(pet, headers);
        ResponseEntity<Pet> result = restTemplate.exchange(BASE_URL + "/pet", HttpMethod.POST, request, Pet.class);

        assertEquals("Bobbie", result.getBody().getName());
    }

    @Test
    public void findPetByIdTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity  = new HttpEntity<>(headers);
        ResponseEntity<Pet> result = restTemplate.exchange(BASE_URL + "/pet/1", HttpMethod.GET, entity, Pet.class);
        assertEquals("Bobbie", result.getBody().getName());
    }

    @Test
    public void updatePetTest() {
        Pet pet = new Pet();
        pet.setId(2);
        pet.setName("Duke");
        pet.setStatus(PetStatus.PENDING);

        HttpHeaders headers = new HttpHeaders();
        headers.set("location", BASE_URL + "/pet/{petId}");
        HttpEntity<Pet> request = new HttpEntity<>(pet, headers);
        ResponseEntity<Pet> result = restTemplate.exchange(BASE_URL + "/pet", HttpMethod.PUT, request, Pet.class);

        assertEquals("Duke", result.getBody().getName());
    }

    @Test
    public void findPetByStatus() {
        ResponseEntity<Pet[]> result = restTemplate.getForEntity(BASE_URL + "/pet/findByStatus", Pet[].class);  //array van een klasse (als klasse notatie)
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updatePetByIdTest() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name", "Robbie");
        parameters.add("status", PetStatus.AVAILABLE.toString());
        ResponseEntity<ApiResponse> result = restTemplate.postForEntity(BASE_URL + "/pet/{petId}", parameters, ApiResponse.class, 1L);
        assertEquals(200, result.getBody().getCode());
    }

    @Test
    public void deletePetByIdTest() {
        restTemplate.delete(BASE_URL + "/pet/1");
        assertThrows(HttpClientErrorException.class, () -> {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        });
    }

}
