package br.com.orange.domain

import br.com.orange.KeyManagerRequest
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class RegistrarChaveRequest(
    @field:NotBlank val clienteId: String,
    @field:NotBlank val tipoChave: String,
    @field:NotBlank val valorChave: String,
    @field:NotBlank val tipoConta: String
    ) {
    fun toGrpc() = KeyManagerRequest.newBuilder()
        .setClientId(this.clienteId)
        .setKeyType(KeyManagerRequest.Key.valueOf(this.tipoChave))
        .setKeyValue(this.valorChave)
        .setAccountType(KeyManagerRequest.Account.valueOf(this.tipoConta))
        .build()
}