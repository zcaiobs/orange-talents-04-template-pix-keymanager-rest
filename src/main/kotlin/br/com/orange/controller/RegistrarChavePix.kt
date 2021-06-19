package br.com.orange.controller

import br.com.orange.KeyManagerRegisterServiceGrpc.*
import br.com.orange.domain.RegistrarChaveRequest
import br.com.orange.domain.RegistrarChaveResponse
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/api/pix")
@Validated
class RegistrarChavePix(private val registraChaveClient: KeyManagerRegisterServiceBlockingStub) {

    @Post
    fun registrar(@Body @Valid registraChaveRequest: RegistrarChaveRequest): HttpResponse<RegistrarChaveResponse> {
        return try {
            val result = registraChaveClient.register(registraChaveRequest.toGrpc())
            val resp = RegistrarChaveResponse(result.message, result.idPix)
            HttpResponse.ok(resp)
        } catch (e: StatusRuntimeException) {
            val ex = e.status.code

            if (ex == Status.ALREADY_EXISTS.code) {
                HttpResponse.unprocessableEntity()
            } else {
                HttpResponse.badRequest()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            HttpResponse.badRequest()
        }
    }
}