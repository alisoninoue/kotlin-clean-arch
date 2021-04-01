package br.com.poc.entrypoints.kafka.producer

import br.com.poc.PersonCreated
import br.com.poc.core.entity.person.Person
import br.com.poc.core.usecases.person.PersonProducerEvent
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.header.internals.RecordHeaders
import java.util.*
import javax.inject.Singleton

@Singleton
class PersonProducerImpl(val personProducer: PersonProducer) : PersonProducerEvent {
    override fun sendEventPersonCreated(person: Person) {
        personProducer.sendPersonCreated(
            "personCreated-${UUID.randomUUID()}",
            person.toAvro(),
            RecordHeaders(listOf(RecordHeader("correlationId", UUID.randomUUID().toString().toByteArray())))
        )
    }

    private fun Person.toAvro(): PersonCreated {
        return PersonCreated.newBuilder()
            .setCpf(cpf)
            .setName(name).build()
    }
}