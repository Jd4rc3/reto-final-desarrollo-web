package org.sofka.mykrello.utilities;

import lombok.Data;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomModelMapper {
    @Bean
    public ModelMapper getModelMapper() {
        var customModelMapper = new ModelMapper();
        customModelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        return customModelMapper;
    }
}
