package com.luiz.kotlinkafkadocker.producer

import com.luiz.kotlinkafkadocker.entity.Person
import com.luiz.kotlinkafkadocker.entity.PersonDTO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import java.time.LocalDate

@Component
class PersonProducerImpl(
    private val pessoaTemplate: KafkaTemplate<String, PersonDTO>
) {
    val topicName = "person-v1"

    fun persist(messageId: String, payload: Person){
        val dto = createDTO(payload)
        sendPessoaMessage(messageId, dto)
    }

    private fun sendPessoaMessage(messageId: String, dto: PersonDTO) {
        val message = createMessageWithHeaders(messageId, dto, topicName)

        val future: ListenableFuture<SendResult<String, PersonDTO>> = pessoaTemplate.send(message)

        future.addCallback(object: ListenableFutureCallback<SendResult<String, PersonDTO>> {
            override fun onSuccess(result: SendResult<String, PersonDTO>?) {
                println("Person sent. MessageId $messageId")
            }
            override fun onFailure(ex: Throwable) {
                println("Error to send menssage. MessageId $messageId")
            }
        })

    }

    private fun createDTO(payload: Person): PersonDTO {
        return PersonDTO.newBuilder()
            .setName(payload.name)
            .setLastname(payload.lastname)
            .build()
    }

    private fun createMessageWithHeaders(messageId: String, pessoaDTO: PersonDTO, topic: String): Message<PersonDTO> {
        return MessageBuilder.withPayload(pessoaDTO)
            .setHeader("hash", pessoaDTO.hashCode())
            .setHeader("version", "1.0.0")
            .setHeader("endOfLife", LocalDate.now().plusDays(1L))
            .setHeader("type", "fct")
            .setHeader("cid", messageId)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.MESSAGE_KEY, messageId)
            .build()
    }
}