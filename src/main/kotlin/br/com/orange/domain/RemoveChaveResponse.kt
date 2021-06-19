package br.com.orange.domain

import io.micronaut.core.annotation.Introspected

@Introspected
data class RemoveChaveResponse(val deletedAt: String)