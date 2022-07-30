package org.sofka.mykrello.utilities;

import lombok.Data;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @LuisaAvila @DanielArce
 * CustomModelMapper this class is used to create a bean from the modelMapper library and configure it to avoid null fields.
 */
@Data
@Component
public class CustomModelMapper {

    /**
     * @LuisaAvila @DanielArce     *
     * @return this function will retonate a configured modelMapper bean
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