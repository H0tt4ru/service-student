package com.example.service_student.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base_domain.dto.model.Student;
import com.example.service_student.request.StudentRequest;
import com.example.service_student.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/student/my-profile")
    public ResponseEntity<Object> getMyProfile() throws Exception {
        return studentService.getMyProfile();
    }

    @PutMapping("/student/update-profile")
    public ResponseEntity<Object> updateProfile(@RequestBody StudentRequest student) throws Exception {
        return studentService.updateProfile(student);
    }

    @DeleteMapping("/student/delete-profile")
    public ResponseEntity<Object> deleteProfile() throws Exception {
        return studentService.deleteProfile();
    }

    @GetMapping("/admin/students")
    public ResponseEntity<Object> getStudent() throws Exception {
        return studentService.getStudents();
    }

}
