package br.com.orange.domain

import br.com.orange.KeyManagerFindRequest.*
import io.micronaut.core.annotation.Introspected

@Introspected
data class ConsultarChaveRequest(
    val clienteId: String,
    val pixId: String,
    val valorChave: String
) {

    fun toGrpc() = newBuilder()
        .setKeymanagerFind(KeyManagerFind.newBuilder().setClientId(this.clienteId))
        .setKeymanagerFind(KeyManagerFind.newBuilder().setIdPix(this.pixId))
        .setKeyValue(this.valorChave)
        .build()
}