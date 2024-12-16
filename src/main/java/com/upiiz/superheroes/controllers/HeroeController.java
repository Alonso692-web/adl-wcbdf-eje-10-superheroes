package com.upiiz.superheroes.controllers;

import com.upiiz.superheroes.entities.HeroeEntity;
import com.upiiz.superheroes.responses.CustomResponseHeroe;
import com.upiiz.superheroes.responses.CustomResponseUnHeroe;
import com.upiiz.superheroes.services.HeroeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/heroes")
@Tag(
        name = "Superheroes"
)
public class HeroeController {

    @Autowired
    HeroeService heroeService;

    @GetMapping
    public ResponseEntity<CustomResponseHeroe<List<HeroeEntity>>> getHeroe() {
        List<HeroeEntity> heroes = new ArrayList<>();
        Link allHeroesLink = linkTo(HeroeController.class).withSelfRel();
        List<Link> links = List.of(allHeroesLink);
        try {
            heroes = heroeService.getAllHeroes();
            if (!heroes.isEmpty()) {
                CustomResponseHeroe<List<HeroeEntity>> response = new CustomResponseHeroe<>(1, "Heroes encontrados", heroes, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponseHeroe<>(0, "Heroes no encontrados", heroes, links));
            }
        } catch (Exception e) {
            CustomResponseHeroe<List<HeroeEntity>> response = new CustomResponseHeroe<>(500, "Error interno de servidor", heroes, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponseUnHeroe<HeroeEntity>> getHeroeById(@PathVariable Long id) {
        Optional<HeroeEntity> hereoe = null;
        CustomResponseUnHeroe<HeroeEntity> response = null;
        Link allHeroesLink = linkTo(HeroeController.class).withSelfRel();
        List<Link> links = List.of(allHeroesLink);
        try {
            hereoe = Optional.ofNullable(heroeService.getHeroeById(id));
            if (hereoe.isPresent()) {
                response = new CustomResponseUnHeroe<>(1, "Heroe encontrado", hereoe.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponseUnHeroe<>(0, "Heroe no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponseUnHeroe<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponseUnHeroe<HeroeEntity>> crearHeroe(@RequestBody HeroeEntity heroe) {
        Link allHeroesLink = linkTo(HeroeController.class).withSelfRel();
        List<Link> links = List.of(allHeroesLink);
        try {
            HeroeEntity heroeEntity = heroeService.createHeroe(heroe);
            if (heroeEntity != null) {
                CustomResponseUnHeroe<HeroeEntity> response = new CustomResponseUnHeroe<>(1, "Heroe creado", heroeEntity, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponseUnHeroe<>(0, "Heroe no encontrado", heroeEntity, links));
            }
        } catch (Exception e) {
            CustomResponseUnHeroe<HeroeEntity> response = new CustomResponseUnHeroe<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // PUT DE UN HEROE
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponseUnHeroe<HeroeEntity>> updateHeroe(@RequestBody HeroeEntity heroe, @PathVariable Long id) {
        Link allHeroesLink = linkTo(HeroeController.class).withSelfRel();
        List<Link> links = List.of(allHeroesLink);
        try {
            heroe.setId(id);
            if (!heroeService.getHeroeById(id).equals("")) {
                HeroeEntity heroeEntity = heroeService.updateHeroe(heroe);
                CustomResponseUnHeroe<HeroeEntity> response = new CustomResponseUnHeroe<>(1, "Heroe actualizado", heroeEntity, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                CustomResponseUnHeroe<HeroeEntity> response = new CustomResponseUnHeroe<>(0, "Heroe no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            CustomResponseUnHeroe<HeroeEntity> response = new CustomResponseUnHeroe<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // DELETE UN HEROE
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponseUnHeroe<HeroeEntity>> deleteHeroeById(@PathVariable Long id) {
        Optional<HeroeEntity> heroeEntity = null;
        CustomResponseUnHeroe<HeroeEntity> response = null;
        Link allHeroesLink = linkTo(HeroeController.class).withSelfRel();
        List<Link> links = List.of(allHeroesLink);

        try {
            heroeEntity = Optional.ofNullable(heroeService.getHeroeById(id));
            if (heroeEntity.isPresent()) {
                heroeService.deleteHeroe(id);
                response = new CustomResponseUnHeroe<>(1, "Heroe eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponseUnHeroe<>(0, "Heroe no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponseUnHeroe<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}


