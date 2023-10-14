package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Request;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Response;

@Service
@Qualifier("ModifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {
    @Override
    public void sendTime(Response response, Request request){

        request.setSystemTime(response.getSystemTime());

    }
    @Override
    public void modify(Request request){

        request.setSource("source 1");

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}