package br.com.poc.core.dataproviders

import br.com.poc.core.models.person.Person
import java.util.*

interface PersonRepository {
    fun register(person: Person)
    fun findBy(cpf: Long) : Optional<Person>
}