package com.example.jspDemo.repo;

import com.example.jspDemo.modal.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnimeRepository extends JpaRepository<Anime, Long> {
}
