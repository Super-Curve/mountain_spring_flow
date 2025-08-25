package com.mountain.python.application.service;

import com.mountain.common.application.UseCase;
import com.mountain.python.domain.model.PythonScript;
import com.mountain.python.domain.model.ScriptResult;
import com.mountain.python.infrastructure.executor.PythonExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PythonScriptService implements UseCase {

    private final PythonExecutor pythonExecutor;

    public ScriptResult executeScript(PythonScript script) {
        return pythonExecutor.execute(script);
    }

    public ScriptResult executeScript(String scriptContent) {
        PythonScript script = PythonScript.builder()
                .content(scriptContent)
                .build();
        return executeScript(script);
    }
}