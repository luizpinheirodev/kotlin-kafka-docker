package com.luiz.kotlinkafkadocker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinKafkaDockerApplication

fun main(args: Array<String>) {
	runApplication<KotlinKafkaDockerApplication>(*args)
}
