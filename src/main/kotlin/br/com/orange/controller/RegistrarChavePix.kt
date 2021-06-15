package br.com.orange.controller

import br.com.orange.KeyManagerRegisterServiceGrpc.*
import br.com.orange.domain.RegistrarChaveRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/api/pix")
@Validated
class RegistrarChavePix(val registraChaveClient: KeyManagerRegisterServiceBlockingStub) {

    @Post
    fun registrar(@Valid registraChaveRequest: RegistrarChaveRequest): HttpResponse<String> {
        return try {
            val result = registraChaveClient.register(registraChaveRequest.toGrpc())
            HttpResponse.ok(result.idPix)
        } catch (ex: Exception) {
            HttpResponse.unprocessableEntity()
        }
    }
}