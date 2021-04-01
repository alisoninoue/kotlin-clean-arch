package br.com.poc.application.services

import br.com.poc.PersonCreated
import br.com.poc.entrypoints.kafka.producer.PersonProducer
import br.com.poc.dataproviders.entities.PersonEntity
import br.com.poc.dataproviders.PersonJpaRepository
import br.com.poc.entrypoints.grpc.PersonService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.util.*

@Disabled
internal class PersonServiceTest {
    @Test
    internal fun name() {

        val producer = mockk<PersonProducer>()
        val repository = mockk<PersonJpaRepository>()
//        val service = PersonService(repository, producer)

        every {
            producer.sendPersonCreated(ofType(String::class), ofType(PersonCreated::class), any())
        } returns Unit

        every {
            repository.findById(any())
        } returns Optional.empty()

        every {
            repository.save(any())
        } returns PersonEntity(123456L, "Alison")

//        service.savePerson(12345678901L, "Alison")

        verify(atMost = 1) { repository.findById(any()) }
        verify(atMost = 1) { repository.save(any()) }
        verify(atMost = 1) { producer.sendPersonCreated(ofType(String::class), ofType(PersonCreated::class), any()) }

    }
}
