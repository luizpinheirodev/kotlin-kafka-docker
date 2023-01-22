package com.luiz.kotlinkafkadocker.consumer

import com.luiz.kotlinkafkadocker.entity.Person
import com.luiz.kotlinkafkadocker.entity.PersonDTO
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class PersonConsumerImpl {

    @KafkaListener(id = "pessoa-consumer",
        topicPartitions = [
            TopicPartition(
                topic = "person-v1",
                partitions = ["0"],
                partitionOffsets = arrayOf(PartitionOffset(partition = "*", initialOffset = "0"))
            )
        ]
    )

    fun consume(@Payload personDTO: PersonDTO){
        val pessoa = Person(personDTO.getName().toString(), personDTO.getLastname().toString())
        println("Person received")
        println(pessoa.toString())
    }

}