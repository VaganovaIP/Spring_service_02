package ru.students.test_rest_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.students.test_rest_service.model.Request;
import ru.students.test_rest_service.model.Response;

import javax.validation.constraints.NotNull;

@RestController
public class MyController {

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody @NotNull Request request){

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemtime(request.getSystemtime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
