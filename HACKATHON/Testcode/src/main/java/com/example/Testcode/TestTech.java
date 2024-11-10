package com.example.Testcode;

import com.example.Testcode.PDFread.PDFExtractor;
import com.example.Testcode.models.Find;

import java.io.File;
import java.io.IOException;

public class TestTech {
    public static void main(String[] args) throws IOException {

        PDFExtractor pdfExtractor = new PDFExtractor();
        File pdfFile = new File("C:/Users/Ancikor PC/Desktop/HACKATHON/rez.pdf");

        String pdfText = pdfExtractor.extractTextFromPDF(pdfFile);
        Find candidate = pdfExtractor.parseCandidateData(pdfText);

        System.out.println("Имя: " + candidate.getName());
        System.out.println("Контактная информация: " + candidate.getContactInfo());
        System.out.println("Должность: " + candidate.getPosition());
        System.out.println("Обязанности: " + candidate.getResponsibilities());
        System.out.println("Навыки: " + candidate.getSkills());
        System.out.println("Языки: " + candidate.getLanguages());
        System.out.println("Образование: " + candidate.getEducation());


    }
}
