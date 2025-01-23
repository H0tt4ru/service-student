package com.example.service_student.response;

import java.util.List;

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
public class StudentListResponse extends BaseResponse {

    private List<Object> students;
}
