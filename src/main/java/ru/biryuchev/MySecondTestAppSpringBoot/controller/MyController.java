package ru.biryuchev.MySecondTestAppSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.biryuchev.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.biryuchev.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.biryuchev.MySecondTestAppSpringBoot.model.*;
import ru.biryuchev.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.biryuchev.MySecondTestAppSpringBoot.service.ValidationService;
import ru.biryuchev.MySecondTestAppSpringBoot.util.DataTimeUtil;

import javax.validation.Valid;
import java.util.Date;


@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DataTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCES)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("response: {}", response);

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("ValidationFailedException: {} {}", response, bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_CODE_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UID123);
            log.error("UnsupportedCodeException: {} {} ", response, bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Exception: {} {}", response, bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}