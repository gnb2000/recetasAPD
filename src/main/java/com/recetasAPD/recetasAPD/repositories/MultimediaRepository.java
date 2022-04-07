package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia,Integer> {
}
