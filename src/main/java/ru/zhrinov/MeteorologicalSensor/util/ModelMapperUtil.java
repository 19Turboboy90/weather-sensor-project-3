package ru.zhrinov.MeteorologicalSensor.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S, T> T convertToEntity(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
