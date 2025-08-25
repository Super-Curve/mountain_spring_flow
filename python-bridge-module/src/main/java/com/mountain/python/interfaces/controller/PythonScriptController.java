package com.mountain.python.interfaces.controller;

import com.mountain.common.interfaces.dto.BaseResponse;
import com.mountain.python.application.service.PythonScriptService;
import com.mountain.python.domain.model.ScriptResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/python")
@RequiredArgsConstructor
public class PythonScriptController {

    private final PythonScriptService pythonScriptService;

    @PostMapping("/execute")
    public BaseResponse<ScriptResult> executeScript(@RequestBody String scriptContent) {
        ScriptResult result = pythonScriptService.executeScript(scriptContent);
        return BaseResponse.success(result);
    }

    @PostMapping("/execute/process")
    public BaseResponse<ScriptResult> executeScriptWithProcess(@RequestBody String scriptContent) {
        ScriptResult result = pythonScriptService.executeScript(scriptContent);
        return BaseResponse.success(result);
    }
}