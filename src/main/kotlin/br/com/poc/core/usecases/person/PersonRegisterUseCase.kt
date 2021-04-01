package br.com.poc.core.usecases.person

import br.com.poc.core.dataproviders.PersonRepository
import br.com.poc.core.entity.person.Person

class PersonRegisterUseCase(private val repository: PersonRepository, private val producer: PersonProducerEvent) :
    PersonUseCase {

    override fun registerPerson(person: Person) {
        repository.findBy(person.cpf)
            .ifPresent { throw InvalidArgumentException("Person ${person.cpf} already exists!") }
        repository.register(person)

        producer.sendEventPersonCreated(person)
    }

    override fun findById(cpf: Long): Person {
        return repository.findBy(cpf).orElseThrow { PersonNotFoundException("Person $cpf not found!") }
    }
}