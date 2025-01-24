package com.example.service_student.request;

import java.time.LocalDate;

import com.example.base_domain.dto.constant.Gender;
import com.example.base_domain.dto.constant.Major;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Major major;

    private String phoneNumber;

    private String address;
}
