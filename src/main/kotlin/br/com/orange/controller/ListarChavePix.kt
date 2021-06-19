package br.com.orange.controller

import br.com.orange.KeyManagerFindAllRequest
import br.com.orange.KeyManagerFindAllServiceGrpc.*
import br.com.orange.domain.ListarChaveResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/api/pix")
class ListarChavePix(private val listarChaveClient: KeyManagerFindAllServiceBlockingStub) {

    @Get("/listar/{clienteId}")
    fun listar(@PathVariable clienteId: String): HttpResponse<Any> {

        val result = listarChaveClient.findAll(
            KeyManagerFindAllRequest.newBuilder()
                .setClienteId(clienteId)
                .build()
        )

        val resp = ListarChaveResponse(result)

        return if (resp.keyList.isNotEmpty()) {
            HttpResponse.ok(resp)
        } else {
            HttpResponse.notFound(resp)
        }
    }
}