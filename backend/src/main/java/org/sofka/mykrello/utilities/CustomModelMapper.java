package org.sofka.mykrello.utilities;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomModelMapper {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
