package br.com.orange.controller

import br.com.orange.KeyManagerFindAllRequest
import br.com.orange.KeyManagerFindAllServiceGrpc.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/api/pix")
class ListarChavePix(val listarChaveClient: KeyManagerFindAllServiceBlockingStub) {

    @Get("/listar/{clienteId}")
    fun listar(@PathVariable clienteId: String): HttpResponse<Any> {
        val result = listarChaveClient.findAll(KeyManagerFindAllRequest.newBuilder()
            .setClienteId(clienteId)
            .build())
        result.chavesList.forEach { println(it.conta) }
        return HttpResponse.ok(result.toString())
    }
}