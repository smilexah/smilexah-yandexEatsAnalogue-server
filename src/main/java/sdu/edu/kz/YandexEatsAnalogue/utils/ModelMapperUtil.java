package sdu.edu.kz.YandexEatsAnalogue.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {
    private final ModelMapper modelMapper = new ModelMapper();

    public ModelMapperUtil() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}