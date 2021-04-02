package br.com.poc.core.usecases.person

import br.com.poc.core.models.person.Person

interface PersonGetterUseCase {
    fun findById(cpf: Long): Person
}