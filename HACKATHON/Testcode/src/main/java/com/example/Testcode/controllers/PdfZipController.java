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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/api/pdf")
public class PdfZipController {

    private final PDFExtractor pdfExtractor = new PDFExtractor(); // Initializing PDFExtractor
    private final FiRepository fiRepository; // Repository for saving data

    // Constructor for FiRepository injection
    public PdfZipController(FiRepository fiRepository) {
        this.fiRepository = fiRepository;
    }

    @PostMapping("/upload-zip")
    public ResponseEntity<String> uploadZip(@RequestParam("zipFile") MultipartFile file) {
        // Check if the file is empty or not a ZIP file
        if (file.isEmpty() || !file.getContentType().equals("application/zip")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Файл должен быть в формате ZIP.");
        }

        StringBuilder extractedText = new StringBuilder();

        try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {

            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                // Check if the entry is a PDF file
                if (!entry.isDirectory() && entry.getName().endsWith(".pdf")) {
                    extractedText.append("Содержимое файла ").append(entry.getName()).append(":\n");

                    // Read PDF content
                    try (PDDocument document = PDDocument.load(zipInputStream)) {
                        PDFTextStripper pdfStripper = new PDFTextStripper();
                        String pdfText = pdfStripper.getText(document);
                        extractedText.append(pdfText).append("\n\n");

                        // Extract data from the PDF text using PDFExtractor
                        Find candidate = pdfExtractor.parseCandidateData(pdfText);

                        // Save fields individually
                        saveField(candidate, "name", candidate.getName());
                        saveField(candidate, "contactInfo", candidate.getContactInfo());
                        saveField(candidate, "position", candidate.getPosition());
                        saveField(candidate, "responsibilities", candidate.getResponsibilities());
                        saveField(candidate, "skills", candidate.getSkills());
                        saveField(candidate, "languages", candidate.getLanguages());
                        saveField(candidate, "education", candidate.getEducation());

                        // Save the candidate object to the database
                        fiRepository.save(candidate);
                    }
                }
                zipInputStream.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка обработки ZIP файла.");
        }

        return ResponseEntity.ok("Успешно выполнено!");
    }

    // Method to save each field into the database
    private void saveField(Find candidate, String fieldName, String fieldValue) {
        // Check if the field value is not null or empty
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
                default:
                    break;
            }
        }
    }
}
