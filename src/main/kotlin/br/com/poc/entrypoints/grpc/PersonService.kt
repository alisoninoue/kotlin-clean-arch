package br.com.poc.entrypoints.grpc

import br.com.poc.PersonDetails
import br.com.poc.core.entity.person.Person
import br.com.poc.core.usecases.person.PersonUseCase
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag
import io.micronaut.transaction.annotation.ReadOnly
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
open class PersonService(val personUseCase: PersonUseCase) {

    @Transactional
    @ContinueSpan
    open fun savePerson(@SpanTag("cpf") cpf: Long, name: String) {
        println("cpf: $cpf + name: $name")
        personUseCase.registerPerson(Person(cpf, name))
    }

    @ReadOnly
    @ContinueSpan
    open fun findBy(cpf: Long): PersonDetails {
        val person = personUseCase.findById(cpf)
        return PersonDetails.newBuilder()
            .setCpf(person.cpf)
            .setName(person.name)
            .build()
    }
}