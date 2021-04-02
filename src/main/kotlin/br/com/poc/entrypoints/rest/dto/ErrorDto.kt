package br.com.poc.entrypoints.rest.dto

import io.micronaut.core.annotation.Introspected

@Introspected
data class ErrorDto(
    val errors: List<String>
)