/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

@Configuration
@ComponentScan("ua.edu.ukma.thkeys")
public class ContextConfiguration {
    
    @Bean
    @Autowired
    public ConversionServiceFactoryBean conversionService(final Set<Converter> converters) {
        ConversionServiceFactoryBean csfb = new ConversionServiceFactoryBean();
        csfb.setConverters(converters);
        return csfb;
    }
}
