package com.example.service_student.request;

import com.example.base_domain.dto.model.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequest {

    private String nim;
    private Student student;
}
