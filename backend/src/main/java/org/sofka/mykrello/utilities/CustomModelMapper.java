package org.sofka.mykrello.utilities;

import lombok.Data;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author LuisaAvila <luisaavila304@gmail.com>
 * CustomModelMapper this class is used to create a bean from the modelMapper library and
 * configure it to avoid null fields.
 */
@Data
@Component
public class CustomModelMapper {

    /**
     * @return this function will return a configured modelMapper bean
     * @author LuisaAvila <luisaavila304@gmail.com>
     */
    @Bean
    public ModelMapper getModelMapper() {
        var customModelMapper = new ModelMapper();
        var config = customModelMapper.getConfiguration();
        config.setPropertyCondition(Conditions.isNotNull());
        config.setMatchingStrategy(MatchingStrategies.STRICT);

        return customModelMapper;
    }
}