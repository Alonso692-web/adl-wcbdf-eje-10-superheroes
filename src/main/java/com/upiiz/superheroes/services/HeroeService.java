package com.upiiz.superheroes.services;

import com.upiiz.superheroes.entities.HeroeEntity;
import com.upiiz.superheroes.repositories.HeroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroeService {
    @Autowired
    HeroeRepository heroeRepository;

    // GET ALL
    public List<HeroeEntity> getAllHeroes(){
        return heroeRepository.findAll();
    }
    // GET por Id
    public HeroeEntity getHeroeById(Long id){
        return heroeRepository.findHeroeById(id);
    }
    // POST
    public HeroeEntity createHeroe(HeroeEntity heroe){
        return heroeRepository.save(heroe);
    }
    // PUT Heroe
    public HeroeEntity updateHeroe(HeroeEntity heroe){
        return heroeRepository.save(heroe);
    }
    // DELETE Heroe
    public void deleteHeroe(Long id){
        heroeRepository.deleteById(id);
    }


}
