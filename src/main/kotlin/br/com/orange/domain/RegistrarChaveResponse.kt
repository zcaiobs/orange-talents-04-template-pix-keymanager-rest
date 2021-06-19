package br.com.orange.domain

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class RegistrarChaveResponse(@JsonProperty val messagem: String, @JsonProperty val pixId: String)