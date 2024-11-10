package com.example.Testcode.controllers;

import com.example.Testcode.services.ProFind;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FController {

    private final ProFind proFind;

    // Метод для обработки GET-запроса на главную страницу с поиском
    @GetMapping("/")
    public String ReFind(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            // Если ключевое слово передано, ищем соответствующие данные
            model.addAttribute("ReFind", proFind.findByKeyword(keyword));  // Метод для поиска по ключевому слову
        } else {
            model.addAttribute("ReFind", proFind.list());  // Если поиска нет, показываем все
        }
        return "ReFind";  // Название вашего HTML-шаблона
    }
}
