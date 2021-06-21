package br.com.orange.domain

import br.com.orange.KeyManagerFindAllReply
import io.micronaut.core.annotation.Introspected

@Introspected
data class ChavesResponse(val pixId: String, val keyType: String, val keyValue: String, val account: String, val created: String){
    companion object {
        fun toResponse(key: KeyManagerFindAllReply.ListaDeChaves): ChavesResponse {
            return ChavesResponse(key.pixId, key.keyType.name, key.keyValue, key.conta.name, key.created)
        }
    }
}




