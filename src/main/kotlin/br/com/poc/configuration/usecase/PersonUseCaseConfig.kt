package br.com.poc.configuration.usecase

import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.usecases.person.PersonProducerEvent
import br.com.poc.core.usecases.person.PersonRegisterUseCase
import br.com.poc.core.usecases.person.PersonUseCase
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
open class PersonUseCaseConfig {

    @Singleton
    open fun loadUseCase(personRepository: PersonRepository, personProducerEvent: PersonProducerEvent): PersonUseCase =
        PersonRegisterUseCase(personRepository, personProducerEvent)
}