package com.mountain.python.infrastructure.executor;

import com.mountain.python.domain.model.PythonScript;
import com.mountain.python.domain.model.ScriptResult;
import lombok.extern.slf4j.Slf4j;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class PythonExecutor {

    public ScriptResult execute(PythonScript script) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 使用Jython执行Python脚本
            StringWriter outputWriter = new StringWriter();
            StringWriter errorWriter = new StringWriter();
            
            PythonInterpreter interpreter = new PythonInterpreter();
            interpreter.setOut(outputWriter);
            interpreter.setErr(errorWriter);
            
            interpreter.exec(script.getContent());
            
            String output = outputWriter.toString();
            String error = errorWriter.toString();
            
            long executionTime = System.currentTimeMillis() - startTime;
            
            return new ScriptResult(
                error.isEmpty(),
                output,
                error,
                executionTime
            );
            
        } catch (Exception e) {
            log.error("Error executing Python script", e);
            return new ScriptResult(
                false,
                "",
                e.getMessage(),
                System.currentTimeMillis() - startTime
            );
        }
    }

    public ScriptResult executeWithProcess(String scriptContent) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 创建临时Python文件
            File tempFile = File.createTempFile("script_", ".py");
            tempFile.deleteOnExit();
            
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(scriptContent);
            }
            
            // 使用ProcessBuilder执行Python
            ProcessBuilder processBuilder = new ProcessBuilder("python", tempFile.getAbsolutePath());
            processBuilder.redirectErrorStream(true);
            
            Process process = processBuilder.start();
            
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            
            int exitCode = process.waitFor();
            long executionTime = System.currentTimeMillis() - startTime;
            
            return new ScriptResult(
                exitCode == 0,
                output.toString(),
                exitCode != 0 ? "Process exited with code: " + exitCode : "",
                executionTime
            );
            
        } catch (IOException | InterruptedException e) {
            log.error("Error executing Python process", e);
            return new ScriptResult(
                false,
                "",
                e.getMessage(),
                System.currentTimeMillis() - startTime
            );
        }
    }
}