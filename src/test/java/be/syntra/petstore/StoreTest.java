package be.syntra.petstore;

import be.syntra.petstore.domain.Order;
import be.syntra.petstore.domain.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StoreTest {

    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void placeOrderTest() {
        Order order = new Order();
        order.setId(1);
        order.setQuantity(1);
        order.setShipDate("2023-05-02T13:04:02.819Z");
        order.setStatus(OrderStatus.PLACED);
        order.setComplete(false);

        HttpEntity<Order> entity = new HttpEntity<>(order);
        ResponseEntity<Order> result = restTemplate.postForEntity(PetTest.BASE_URL + "/store/order", entity, Order.class);

        assertEquals(1, result.getBody().getId());
    }

    @Test
    public void findPurchaseFromOrderIdTest() {
        ResponseEntity<Order> result = restTemplate.getForEntity(PetTest.BASE_URL + "/store/order/1", Order.class);
        assertEquals(OrderStatus.PLACED, result.getBody().getStatus());
    }

    @Test
    public void returnPetInventoryTest() {

    }


}
