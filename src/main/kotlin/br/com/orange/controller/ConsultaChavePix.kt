package br.com.orange.controller

import br.com.orange.KeyManagerFindRequest
import br.com.orange.KeyManagerFindServiceGrpc.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/api/pix")
class ConsultaChavePix(private val consultarChaveClient: KeyManagerFindServiceBlockingStub) {

    @Get("/{clienteId}")
    fun consultar(@PathVariable clienteId: String,
                  @QueryValue(defaultValue = "") pixId: String): HttpResponse<Any> {
        val result = consultarChaveClient.find(KeyManagerFindRequest.newBuilder()
            .setKeymanagerFind(KeyManagerFindRequest.KeyManagerFind.newBuilder()
                .setClientId(clienteId)
                .setIdPix(pixId))
            .build())

        return HttpResponse.ok(result.toString())
    }
}