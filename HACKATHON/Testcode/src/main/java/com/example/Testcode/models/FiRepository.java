package com.example.Testcode.models;

import com.example.Testcode.models.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FiRepository extends JpaRepository<Find, Long> {
    List<Find> findByNameContaining(String keyword); // Ищет записи, где имя содержит ключевое слово
}
