package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Request;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyRequestService {
    void modify(Request request);
    void sendTime(Response response, Request request);
}
