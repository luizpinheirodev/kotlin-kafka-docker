package com.luiz.kotlinkafkadocker

import com.luiz.kotlinkafkadocker.entity.Person
import com.luiz.kotlinkafkadocker.producer.PersonProducerImpl
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinKafkaDockerApplication(
	val personProducerImpl: PersonProducerImpl
): ApplicationRunner {
	override fun run(args: ApplicationArguments?) {
		val person = Person("Luiz", "Pinheiro")

		personProducerImpl.persist("12345", person)
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinKafkaDockerApplication>(*args)
}
