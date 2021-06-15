package br.com.orange.domain

import br.com.orange.KeyManagerRemoveRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class RemoverChaveRequest(
    @field:NotBlank val clienteId: String,
    @field:NotBlank val pixId: String) {

    fun toGrpc() = KeyManagerRemoveRequest.newBuilder()
        .setClientId(this.clienteId)
        .setIdPix(this.pixId)
        .build()
}