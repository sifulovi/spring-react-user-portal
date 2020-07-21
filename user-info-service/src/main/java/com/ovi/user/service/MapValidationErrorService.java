package com.ovi.user.service;

import com.ovi.user.payload.request.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationService(SignUpRequest user, BindingResult result) {

        Map<String, String> errorMap = new HashMap<>();

        if (user.getPassword().length() < 6) {
            errorMap.put("password", "Password must be at least 6 characters");
        }
        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }


        return null;

    }
}
