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
import ru.biryuchev.MySecondTestAppSpringBoot.service.ModifyRequestService;
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
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("request: {}", request);
        Response response = createResponse(request);

        log.info("response: {}", response);
        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            return retErrorResponse(response, bindingResult, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            return retErrorResponse(response, bindingResult, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return retErrorResponse(response, bindingResult, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        modifyRequestService.modify(request);
        modifyRequestService.sendTime(response, request);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }

    private Response createResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DataTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    ResponseEntity<Response> retErrorResponse(Response response, BindingResult bindingResult, ErrorCodes errorCode,
                                              ErrorMessages errorMessage, HttpStatus httpStatus) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);

        log.error("error response: {} {}", response, bindingResult.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}