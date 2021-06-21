package br.com.orange.domain

import br.com.orange.KeyManagerFindReply
import io.micronaut.core.annotation.Introspected

@Introspected
data class ConsultaChaveResponse (
    var clientId: String,
    var idPix: String,
    var keyType: String,
    var keyValue: String,
    var titular: Titular,
    var conta: Conta,
    var criadoEm: String
) {
    class Titular(val nome: String, val cpf: String)
    class Conta(val nome: String, val agencia: String, val conta: String, val tipo: String)

    companion object {
        fun toResponse(key: KeyManagerFindReply): ConsultaChaveResponse {
            return ConsultaChaveResponse(
                key.clientId,
                key.idPix,
                key.keyType.name,
                key.keyValue,
                Titular(key.titular.name, key.titular.cpf),
                Conta(key.conta.nome, key.conta.agencia, key.conta.conta, key.conta.tipo.name),
                key.criadoEm
            )
        }
    }
}