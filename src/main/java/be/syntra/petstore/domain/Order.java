package be.syntra.petstore.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    private int id;

    private int petId;
    private int quantity;
    private String shipDate;
    private OrderStatus status;
    private boolean complete;
}
