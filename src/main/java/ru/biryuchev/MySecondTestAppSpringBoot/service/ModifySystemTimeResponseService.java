package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Response;
import ru.biryuchev.MySecondTestAppSpringBoot.util.DataTimeUtil;

import java.util.Date;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DataTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }
}
