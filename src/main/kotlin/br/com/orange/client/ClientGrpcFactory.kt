package br.com.orange.client

import br.com.orange.KeyManagerFindAllServiceGrpc
import br.com.orange.KeyManagerFindServiceGrpc
import br.com.orange.KeyManagerRegisterServiceGrpc
import br.com.orange.KeyManagerRemoveServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class ClientGrpcFactory(@GrpcChannel("keyManager") val channel: ManagedChannel) {
    @Singleton
    fun registraChave() = KeyManagerRegisterServiceGrpc.newBlockingStub(channel)
    @Singleton
    fun removerChave() = KeyManagerRemoveServiceGrpc.newBlockingStub(channel)
    @Singleton
    fun consultarChave() = KeyManagerFindServiceGrpc.newBlockingStub(channel)
    @Singleton
    fun listarChave() = KeyManagerFindAllServiceGrpc.newBlockingStub(channel)
}