package com.upiiz.superheroes.repositories;

import com.upiiz.superheroes.entities.HeroeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<HeroeEntity, Long> {
    @Query("SELECT h FROM HeroeEntity h WHERE h.id = :id")
    HeroeEntity findHeroeById(@Param("id") Long id);
}
