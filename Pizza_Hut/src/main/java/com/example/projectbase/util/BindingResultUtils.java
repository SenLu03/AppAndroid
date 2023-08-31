package com.example.projectbase.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class BindingResultUtils {

    public static ResponseEntity<?> bindResult(BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();

                bindingResult.getFieldErrors().forEach(
                        error -> errors.put(error.getField(), error.getDefaultMessage())
                );
                String errorMsg = "";
                for (String key : errors.keySet()) {
                    errorMsg += "Error in: " + key + ", Reason: " + errors.get(key) + "\n";
                }
                return ResponseEntity.ok(errorMsg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
