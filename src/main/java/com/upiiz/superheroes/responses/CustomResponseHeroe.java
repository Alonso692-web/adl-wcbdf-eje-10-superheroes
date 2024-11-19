package com.upiiz.superheroes.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseHeroe<T> {

    private int estado;
    private String msg;
    private T hereoes;
    private List<Link> links;

}
