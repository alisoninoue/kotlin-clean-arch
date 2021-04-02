package br.com.poc.entrypoints.rest

import br.com.poc.core.models.Person
import br.com.poc.core.usecases.person.PersonGetterUseCase
import br.com.poc.core.usecases.person.PersonRegisterUseCase
import br.com.poc.entrypoints.rest.dto.PersonDto
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/people")
class PersonRest(
    private val personRegisterUseCase: PersonRegisterUseCase,
    private val personGetterUseCase: PersonGetterUseCase
) {

    @Get("/{cpf}")
    fun findByCpf(cpf: Long): HttpResponse<*> {
        val personDto = personGetterUseCase.findById(cpf).toDto()
        return HttpResponse.ok(personDto)
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Post
    fun save(@Body person: PersonDto): HttpResponse<*> {
        personRegisterUseCase.registerPerson(person.toDomain())
        return HttpResponse.ok("")
    }

    private fun Person.toDto(): PersonDto {
        return PersonDto(cpf, name)
    }

    private fun PersonDto.toDomain(): Person {
        return Person(cpf, name)
    }
}