package com.example.Testcode.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pdf_data")
@AllArgsConstructor
@NoArgsConstructor
public class Find {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_e", columnDefinition = "text")
    private String name;
    @Column(name = "contact_info", columnDefinition = "text")
    private String contactInfo;
    @Column(name = "position", columnDefinition = "text")
    private String position;
    @Column(name = "responsibilities", columnDefinition = "text")
    private String responsibilities;
    @Column(name = "skills", columnDefinition = "text")
    private String skills;
    @Column(name = "languages", columnDefinition = "text")
    private String languages;
    @Column(name = "education", columnDefinition = "text")
    private String education;

    // Геттеры и сеттеры

    @Override
    public String toString() {
        return "Find{" +
                "name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", position='" + position + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                ", skills='" + skills + '\'' +
                ", languages='" + languages + '\'' +
                ", education='" + education + '\'' +
                '}';
    }
}
