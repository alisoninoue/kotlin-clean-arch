package br.com.poc.entrypoints.kafka.producer

import br.com.poc.PersonCreated
import br.com.poc.core.entity.person.Person
import br.com.poc.core.usecases.person.PersonProducerEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.tracing.annotation.NewSpan
import org.apache.kafka.common.header.Headers
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.header.internals.RecordHeaders
import java.util.*

@KafkaClient
interface PersonProducer  {

    @Topic("person-created")
    fun sendPersonCreated(@KafkaKey key: String, person: PersonCreated, headers: Headers)
}