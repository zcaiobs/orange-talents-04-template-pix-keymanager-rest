package br.com.orange.domain

import br.com.orange.KeyManagerFindAllReply
import io.micronaut.core.annotation.Introspected

@Introspected
data class ListarChaveResponse(val clientID: String, val keyList: MutableList<ChavesResponse>){
    companion object{
        fun toResponse (lista: KeyManagerFindAllReply): ListarChaveResponse {
            return ListarChaveResponse(lista.clienteId, lista.chavesList.map {
                ChavesResponse.toResponse(it)
            }.toMutableList())
        }
    }
}


