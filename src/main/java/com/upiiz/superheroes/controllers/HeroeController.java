package com.upiiz.superheroes.controllers;

import com.upiiz.superheroes.entities.HeroeEntity;
import com.upiiz.superheroes.services.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroeController {

    @Autowired
    HeroeService heroeService;

    @GetMapping
    public ResponseEntity<List<HeroeEntity>> getHeroes() {
        return ResponseEntity.ok(heroeService.getAllHeroes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroeEntity> obtenerHeroeById(@PathVariable Long id) {
        return ResponseEntity.ok(heroeService.getHeroeById(id));
    }

    @PostMapping
    public ResponseEntity<HeroeEntity> crearHeroe(@RequestBody HeroeEntity heroe) {
        return ResponseEntity.ok(heroeService.createHeroe(heroe));
    }
    // PUT DE UN HEROE
    @PutMapping("/{id}")
    public ResponseEntity<HeroeEntity> actualizarHeroe(@RequestBody HeroeEntity heroe, @PathVariable Long id) {
        heroe.setId(id);
        return ResponseEntity.ok(heroeService.updateHeroe(heroe));
    }
    // DELETE UN HEROE
    @DeleteMapping("/{id}")
    public ResponseEntity<HeroeEntity> borrarHeroe(@PathVariable Long id) {
        heroeService.deleteHeroe(id);
        return ResponseEntity.noContent().build();
    }

}
