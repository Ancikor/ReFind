package com.example.Testcode.services;

import com.example.Testcode.models.FiRepository;
import com.example.Testcode.models.Find;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProFind {

    private final FiRepository fiRepository;

    // Метод для поиска по ключевому слову
    public List<Find> findByKeyword(String keyword) {
        return fiRepository.findByNameContaining(keyword);  // Используем метод поиска по имени
    }

    // Метод для получения всех записей
    public List<Find> list() {
        return fiRepository.findAll();  // Возвращаем все записи из базы
    }
}
