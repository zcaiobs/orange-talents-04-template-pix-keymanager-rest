package br.com.orange.domain

import br.com.orange.KeyManagerFindReply

class ConsultaChaveResponse(keyManagerFindReply: KeyManagerFindReply) {
    val clientId: String = keyManagerFindReply.clientId
    val idPix: String = keyManagerFindReply.idPix
    val keyType: String = keyManagerFindReply.keyType.name
    val keyValue: String = keyManagerFindReply.keyValue
    val titular: Titular = Titular(
        keyManagerFindReply.titular.name,
        keyManagerFindReply.titular.cpf
    )
    val conta: Conta = Conta(
        keyManagerFindReply.conta.nome,
        keyManagerFindReply.conta.agencia,
        keyManagerFindReply.conta.conta,
        keyManagerFindReply.conta.tipo.name
    )
    val criadoEm: String = keyManagerFindReply.criadoEm

    class Titular(val nome: String, val cpf: String)
    class Conta(val nome: String, val agencia: String, val conta: String, val tipo: String)
}