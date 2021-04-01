package br.com.poc.core.usecases.person

import br.com.poc.core.entity.person.Person

interface PersonUseCase {
    fun registerPerson(person: Person)
    fun findById(cpf: Long): Person
}