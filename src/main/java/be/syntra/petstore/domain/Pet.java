package be.syntra.petstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Pet {

    private int id;
    private Category category;
    private String name;
    private String photoUrls;

    private List<Tag> tags;

    private PetStatus status;

}
