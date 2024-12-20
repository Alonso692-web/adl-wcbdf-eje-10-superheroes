package com.upiiz.superheroes.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseUnHeroe<T> {

    private int estado;
    private String msg;
    private T heroe;
    private List<Link> links;

}
