package com.mountain.python.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PythonScript {
    private Long id;
    private String content;
    private String name;
    private String description;
    private ScriptStatus status = ScriptStatus.ACTIVE;
    
    public enum ScriptStatus {
        ACTIVE, INACTIVE
    }
}