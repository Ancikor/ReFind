package com.example.Testcode.controllers;

import com.example.Testcode.PDFread.PDFExtractor;
import com.example.Testcode.models.Find;
import com.example.Testcode.models.FiRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PDFExtractor pdfExtractor = new PDFExtractor(); // Инициализируем PDFExtractor
    private final FiRepository fiRepository; // Репозиторий для сохранения данных

    public PdfController(FiRepository fiRepository) {
        this.fiRepository = fiRepository;
    }

    @PostMapping("/upload-pdf")
    public ResponseEntity<String> uploadPdf(@RequestParam("pdfFile") MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().equals("application/pdf")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Файл должен быть в формате PDF.");
        }

        try {
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);
            document.close();

            Find candidate = pdfExtractor.parseCandidateData(extractedText);
            saveField(candidate, "name", candidate.getName());
            saveField(candidate, "contactInfo", candidate.getContactInfo());
            saveField(candidate, "position", candidate.getPosition());
            saveField(candidate, "responsibilities", candidate.getResponsibilities());
            saveField(candidate, "skills", candidate.getSkills());
            saveField(candidate, "languages", candidate.getLanguages());
            saveField(candidate, "education", candidate.getEducation());

            fiRepository.save(candidate);

            return ResponseEntity.ok("Успешно выполнено!");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка обработки PDF файла.");
        }
    }

    private void saveField(Find candidate, String fieldName, String fieldValue) {
        if (fieldValue != null && !fieldValue.isEmpty()) {
            switch (fieldName) {
                case "name":
                    candidate.setName(fieldValue);
                    break;
                case "contactInfo":
                    candidate.setContactInfo(fieldValue);
                    break;
                case "position":
                    candidate.setPosition(fieldValue);
                    break;
                case "responsibilities":
                    candidate.setResponsibilities(fieldValue);
                    break;
                case "skills":
                    candidate.setSkills(fieldValue);
                    break;
                case "languages":
                    candidate.setLanguages(fieldValue);
                    break;
                case "education":
                    candidate.setEducation(fieldValue);
                    break;
            }
        }
    }
}
