package com.example.service_student.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.base_domain.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NIMGenerator {

    private final StudentRepository studentRepository;

    public String generateUniqueNIM() {
        String generatedNIM;
        do {
            generatedNIM = generateRandomNIM();
        } while (nimExists(generatedNIM));
        return generatedNIM;
    }

    private String generateRandomNIM() {
        Random random = new Random();
        int randomNum = random.nextInt(999999);
        return String.format("%06d", randomNum);
    }

    private boolean nimExists(String nim) {
        return studentRepository.findByNim(nim).isPresent();
    }
}
