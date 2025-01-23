package com.example.service_student.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.base_domain.dto.constant.Gender;
import com.example.base_domain.dto.constant.Major;
import com.example.base_domain.dto.model.Student;
import com.example.base_domain.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentValidation {

    private final StudentRepository studentRepository;

    public boolean validateStudentNew(Student student) throws Exception {
        return validateFullName(student.getFullName()) &&
                validateGender(student.getGender()) &&
                validateDob(student.getDob()) &&
                validateMajor(student.getMajor()) &&
                validatePhoneNumber(student.getPhoneNumber()) &&
                validateAddress(student.getAddress());
    }

    public boolean validateStudentUpdate(Student student) throws Exception {
        return validateFullNameUpdate(student.getFullName()) &&
                validateGenderUpdate(student.getGender()) &&
                validateDobUpdate(student.getDob()) &&
                validateMajorUpdate(student.getMajor()) &&
                validatePhoneNumberUpdate(student.getPhoneNumber()) &&
                validateAddressUpdate(student.getAddress());
    }

    private boolean validateFullName(String fullName) throws Exception {
        if (fullName == null || fullName.length() > 100) {
            throw new Exception("4105");
        }
        return true;
    }

    private boolean validateGender(Gender gender) throws Exception {
        if (gender == null) {
            throw new Exception("4106");
        }
        return true;
    }

    private boolean validateDob(LocalDate dob) throws Exception {
        if (dob == null || !dob.isBefore(LocalDate.now())) {
            throw new Exception("4107");
        }
        return true;
    }

    private boolean validateMajor(Major major) throws Exception {
        if (major == null) {
            throw new Exception("4108");
        }
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null || !phoneNumber.matches("\\d{7,}")
                || studentRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new Exception("4109");
        }
        return true;
    }

    private boolean validateAddress(String address) throws Exception {
        if (address == null || address.length() > 100) {
            throw new Exception("4101");
        }
        return true;
    }

    private boolean validateFullNameUpdate(String fullName) throws Exception {
        if (fullName != null && fullName.length() > 100) {
            throw new Exception("4105");
        }
        return true;
    }

    private boolean validateGenderUpdate(Gender gender) throws Exception {
        if (gender != null && !validateGender(gender)) {
            throw new Exception("4106");
        }
        return true;
    }

    private boolean validateDobUpdate(LocalDate dob) throws Exception {
        if (dob != null && !validateDob(dob)) {
            throw new Exception("4107");
        }
        return true;
    }

    private boolean validateMajorUpdate(Major major) throws Exception {
        if (major != null && !validateMajor(major)) {
            throw new Exception("4108");
        }
        return true;
    }

    private boolean validatePhoneNumberUpdate(String phoneNumber) throws Exception {
        if (phoneNumber != null && !phoneNumber.matches("\\d{7,}")) {
            throw new Exception("4109");
        }
        return true;
    }

    private boolean validateAddressUpdate(String address) throws Exception {
        if (address != null && address.length() > 100) {
            throw new Exception("4101");
        }
        return true;
    }
}
