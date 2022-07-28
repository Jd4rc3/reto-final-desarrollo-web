package org.sofka.mykrello.utilities;

import lombok.Data;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomModelMapper {
    @Bean
    public ModelMapper getModelMapper() {
        var customModelMapper = new ModelMapper();
        var config = customModelMapper.getConfiguration();
        config.setPropertyCondition(Conditions.isNotNull());
        config.setMatchingStrategy(MatchingStrategies.STRICT);

        return customModelMapper;
    }
}