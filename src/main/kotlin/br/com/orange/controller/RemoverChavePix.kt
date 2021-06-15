package br.com.orange.controller

import br.com.orange.KeyManagerRemoveServiceGrpc.*
import br.com.orange.domain.RemoverChaveRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.validation.Validated
import javax.validation.Valid


@Controller("/api/pix")
@Validated
class RemoverChavePix(val removeChaveClient: KeyManagerRemoveServiceBlockingStub) {

    @Delete
    fun remover(@Valid removerChaveRequest: RemoverChaveRequest): HttpResponse<Any> {
        return try {
            val result = removeChaveClient.remove(removerChaveRequest.toGrpc())
            HttpResponse.ok(result.message)
        } catch (ex: Exception) {
            HttpResponse.notFound()
        }
    }
}