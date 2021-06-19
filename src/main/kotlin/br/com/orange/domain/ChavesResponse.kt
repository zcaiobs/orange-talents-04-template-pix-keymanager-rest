package br.com.orange.domain

import br.com.orange.KeyManagerFindAllReply

class ChavesResponse(key: KeyManagerFindAllReply.ListaDeChaves) {
    val pixId: String = key.pixId
    val keyType: String = key.keyType.name
    val keyValue: String = key.keyValue
    val account: String = key.conta.name
    val created: String = key.created
}
