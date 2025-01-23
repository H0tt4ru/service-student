package com.example.service_student.response;

import com.example.base_domain.dto.model.Student;
import com.example.base_domain.dto.model.Wallet;
import com.example.base_domain.dto.response.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse extends BaseResponse {

    private Student student;
    private Wallet wallet;
}
