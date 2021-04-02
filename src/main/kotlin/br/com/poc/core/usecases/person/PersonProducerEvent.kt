package br.com.poc.core.usecases.person

import br.com.poc.core.models.person.Person

interface PersonProducerEvent {
    fun sendEventPersonCreated(person: Person)
}