package com.mountain.python.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptResult {
    private boolean success;
    private String output;
    private String error;
    private long executionTime;
}