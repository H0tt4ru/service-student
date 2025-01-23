package com.example.service_student.response;

import com.example.base_domain.dto.model.Student;
import com.example.base_domain.dto.model.Wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAdminResponse {

    private Student student;
    private Wallet wallet;
}
