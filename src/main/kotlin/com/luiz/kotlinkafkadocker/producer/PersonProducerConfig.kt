package com.luiz.kotlinkafkadocker.producer

import com.luiz.kotlinkafkadocker.entity.PersonDTO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class PersonProducerConfig {
    @Bean
    fun personDTOTemplate(factory: ProducerFactory<String, PersonDTO>): KafkaTemplate<String, PersonDTO> {
        return KafkaTemplate(factory)
    }
}