package com.example.service_student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.base_domain.dto.model.Student;
import com.example.base_domain.dto.model.User;
import com.example.base_domain.dto.model.Wallet;
import com.example.service_student.request.StudentUpdateRequest;
import com.example.service_student.request.StudentRequest;
import com.example.service_student.response.StudentResponse;
import com.example.base_domain.repository.StudentRepository;
import com.example.base_domain.repository.UserRepository;
import com.example.base_domain.repository.WalletRepository;
import com.example.service_student.utils.NIMGenerator;
import com.example.service_student.utils.StudentValidation;
import com.example.service_history.response.StudentAdminResponse;
import com.example.service_history.response.StudentListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentValidation studentValidation;
    private final WalletRepository walletRepository;
    private final NIMGenerator nimGenerator;

    public ResponseEntity<Object> getStudents() throws Exception {
        try {
            List<Student> students = studentRepository.findAll();
            List<Object> studentResponses = new ArrayList<>();

            for (Student student : students) {
                Optional<Wallet> wallet = walletRepository.findByNim(student.getNim());
                StudentAdminResponse studentResponse = new StudentAdminResponse();
                studentResponse.setStudent(student);
                studentResponse.setWallet(wallet.get());
                studentResponses.add(studentResponse);
            }

            StudentListResponse response = new StudentListResponse();
            response.setResponseCode("2002");
            response.setResponseMessage("Student data retrieved successfully");
            response.setStudents(studentResponses);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> getStudent(StudentRequest studentRequest) throws Exception {
        try {
            Optional<Student> student = studentRepository.findByNim(studentRequest.getNim());
            if (student.isPresent()) {
                Optional<Wallet> wallet = walletRepository.findByNim(studentRequest.getNim());
                StudentResponse response = new StudentResponse();
                response.setResponseCode("2002");
                response.setResponseMessage("Student data retrieved successfully");
                response.setStudent(student.get());
                response.setWallet(wallet.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("4301");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteStudent(StudentRequest studentRequest) throws Exception {
        try {
            Optional<Student> student = studentRepository.findByNim(studentRequest.getNim());
            if (student.isPresent()) {
                studentRepository.delete(student.get());
                StudentResponse response = new StudentResponse();
                response.setResponseCode("2002");
                response.setResponseMessage("Student data retrieved successfully");
                response.setStudent(student.get());
                response.setWallet(walletRepository.findByNim(studentRequest.getNim()).get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("4301");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> updateStudent(StudentUpdateRequest studentUpdateRequest) throws Exception {
        try {
            Optional<Student> student = studentRepository.findByNim(studentUpdateRequest.getNim());
            Optional<Wallet> wallet = walletRepository.findByNim(student.get().getNim());
            if (student.isPresent()) {
                if (studentValidation.validateStudentUpdate(studentUpdateRequest.getStudent())) {
                    if (studentUpdateRequest.getStudent().getFullName() != null
                            && !studentUpdateRequest.getStudent().getFullName().isBlank()) {
                        student.get().setFullName(studentUpdateRequest.getStudent().getFullName());
                    }
                    if (studentUpdateRequest.getStudent().getGender() != null
                            && !studentUpdateRequest.getStudent().getGender().toString().isBlank()) {
                        student.get().setGender(studentUpdateRequest.getStudent().getGender());
                    }
                    if (studentUpdateRequest.getStudent().getDob() != null) {
                        student.get().setDob(studentUpdateRequest.getStudent().getDob());
                    }
                    if (studentUpdateRequest.getStudent().getMajor() != null
                            && !studentUpdateRequest.getStudent().getMajor().toString().isBlank()) {
                        student.get().setMajor(studentUpdateRequest.getStudent().getMajor());
                    }
                    if (studentUpdateRequest.getStudent().getPhoneNumber() != null
                            && !studentUpdateRequest.getStudent().getPhoneNumber().isBlank()) {
                        student.get().setPhoneNumber(studentUpdateRequest.getStudent().getPhoneNumber());
                    }
                    if (studentUpdateRequest.getStudent().getAddress() != null
                            && !studentUpdateRequest.getStudent().getAddress().isBlank()) {
                        student.get().setAddress(studentUpdateRequest.getStudent().getAddress());
                    }
                    studentRepository.save(student.get());
                    StudentResponse response = new StudentResponse();
                    response.setResponseCode("2003");
                    response.setResponseMessage("Student updated successfully");
                    response.setStudent(student.get());
                    response.setWallet(wallet.get());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    throw new Exception("4101");
                }
            } else {
                throw new Exception("4301");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> createStudent(Student student) throws Exception {
        try {
            student.setNim(nimGenerator.generateUniqueNIM());
            if (studentValidation.validateStudentNew(student)) {
                Wallet wallet = new Wallet();
                wallet.setNim(student.getNim());
                wallet.setPoint(100);
                studentRepository.save(student);
                walletRepository.save(wallet);
                StudentResponse response = new StudentResponse();
                response.setResponseCode("2000");
                response.setResponseMessage("Registration successful");
                response.setStudent(student);
                response.setWallet(wallet);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("4101");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> getMyProfile() throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Optional<User> user = userRepository.findByEmail(email);
            Optional<Student> student = studentRepository.findByNim(user.get().getStudentNim());
            Optional<Wallet> wallet = walletRepository.findByNim(user.get().getStudentNim());
            if (student.isPresent()) {
                StudentResponse response = new StudentResponse();
                response.setResponseCode("2002");
                response.setResponseMessage("Student data retrieved successfully");
                response.setStudent(student.get());
                response.setWallet(wallet.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("4301");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> updateProfile(Student student) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Optional<User> user = userRepository.findByEmail(email);
            Optional<Student> studentOptional = studentRepository.findByNim(user.get().getStudentNim());
            if (studentOptional.isPresent()) {
                StudentUpdateRequest studentUpdateRequest = new StudentUpdateRequest();
                studentUpdateRequest.setNim(user.get().getStudentNim());
                studentUpdateRequest.setStudent(student);
                return updateStudent(studentUpdateRequest);
            } else {
                throw new Exception("4301");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<Object> deleteProfile() throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Optional<User> user = userRepository.findByEmail(email);
            Optional<Student> student = studentRepository.findByNim(user.get().getStudentNim());
            Optional<Wallet> wallet = walletRepository.findByNim(user.get().getStudentNim());
            if (student.isPresent()) {
                walletRepository.delete(wallet.get());
                studentRepository.delete(student.get());
                userRepository.delete(user.get());
                StudentResponse response = new StudentResponse();
                response.setResponseCode("2002");
                response.setResponseMessage("Student data retrieved successfully");
                response.setStudent(student.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("4301");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
