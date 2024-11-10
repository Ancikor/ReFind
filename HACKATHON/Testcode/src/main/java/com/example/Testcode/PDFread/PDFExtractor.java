package com.example.Testcode.PDFread;

import com.example.Testcode.models.Find;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFExtractor {

    public String extractTextFromPDF(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    public Find parseCandidateData(String pdfText) {
        Find candidate = new Find();

        candidate.setName(extractName(pdfText));
        candidate.setContactInfo(extractContactInfo(pdfText));
        candidate.setPosition(extractPosition(pdfText));
        candidate.setResponsibilities(extractResponsibilities(pdfText));
        candidate.setSkills(extractSkills(pdfText));
        candidate.setLanguages(extractLanguages(pdfText));
        candidate.setEducation(extractEducation(pdfText));

        return candidate;
    }

    private String extractName(String text) {
        Pattern pattern = Pattern.compile("(?i)(Имя|ФИО|Имя кандидата|Фамилия и имя)[:\\-\\s]*(\\p{L}+\\s+\\p{L}+(?:\\s+\\p{L}+)?).*", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2).trim() : "Имя не найдено";
    }

    private String extractContactInfo(String text) {
        Pattern pattern = Pattern.compile(
                "(Тел(?:ефон)?[:\\-\\s]*([\\+\\d\\-()\\s]{7,})|e[-\\s]?mail[:\\-\\s]*([\\w\\.-]+@[\\w\\.-]+))",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        StringBuilder contactInfo = new StringBuilder();

        while (matcher.find()) {
            contactInfo.append(matcher.group().trim()).append("\n");
        }

        return contactInfo.length() > 0 ? contactInfo.toString() : "Контактная информация не найдена";
    }

    private String extractPosition(String text) {
        Pattern pattern = Pattern.compile(
                "(?i)(Должность|Позиция|Роль)[:\\-\\s]*(Аналитик|Трекер|Менеджер проекта|Руководитель проекта|Координатор|Консультант|Специалист).*",
                Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2).trim() : "Должность не найдена";
    }

    private String extractResponsibilities(String text) {
        Pattern pattern = Pattern.compile(
                "(?i)(Ключевые обязанности|Обязанности|Ответственности)[:\\-\\s]*(.*?)(?=(Ключевые обязанности|Навыки|Образование|$))",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2).trim() : "Обязанности не найдены";
    }

    private String extractSkills(String text) {
        Pattern pattern = Pattern.compile(
                "(?i)(Навыки|Ключевые навыки|Компетенции)[:\\-\\s]*(.*?)(?=(Опыт работы|Образование|Языки|$))",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2).trim() : "Навыки не найдены";
    }

    private String extractLanguages(String text) {
        Pattern pattern = Pattern.compile(
                "(?i)(Владение языками|Языки|Иностранные языки)[:\\-\\s]*(.*?)(?=(Навыки|Образование|$))",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2).trim() : "Языки не найдены";
    }

    private String extractEducation(String text) {
        Pattern pattern = Pattern.compile(
                "(?i)(Образование)[:\\-\\s]*(.*?)(?=(Дополнительная информация|Опыт работы|$))",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(2).trim() : "Образование не найдено";
    }
}
