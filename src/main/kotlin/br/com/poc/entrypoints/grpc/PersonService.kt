package br.com.poc.entrypoints.grpc

import br.com.poc.PersonDetails

interface PersonService {
    open fun savePerson(cpf: Long, name: String)
    open fun findBy(cpf: Long): PersonDetails
}