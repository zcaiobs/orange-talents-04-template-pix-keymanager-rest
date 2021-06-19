package br.com.orange.domain

import br.com.orange.KeyManagerFindAllReply

class ListarChaveResponse(lista: KeyManagerFindAllReply) {
    val clientID: String = lista.clienteId
    val keyList: MutableList<ChavesResponse> = lista.chavesList.map {
        ChavesResponse(it)
    }.toMutableList()
}