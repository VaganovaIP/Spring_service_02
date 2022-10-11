package ru.students.test_rest_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.students.test_rest_service.model.Request;
import ru.students.test_rest_service.model.Response;
import ru.students.test_rest_service.service.MyModifyService;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
public class MyController {
    private final MyModifyService myModifyService;
    @Autowired
    public MyController(@Qualifier("ModifySystemMessage") MyModifyService myModifyService){
        this.myModifyService = myModifyService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody @NotNull Request request){

        log.info("Входящий request : " + String.valueOf(request));

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemtime(request.getSystemtime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();
        Response responseAfterModify = myModifyService.modify(response);
        log.info("Исходящий request : " + String.valueOf(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
