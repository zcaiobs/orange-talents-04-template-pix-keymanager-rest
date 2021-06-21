package br.com.orange

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import br.com.orange.KeyManagerFindAllServiceGrpc.*
import br.com.orange.domain.ListarChaveResponse
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import org.junit.jupiter.api.Assertions
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest
class ListarChavePixTest {

    @Singleton
    @Replaces(KeyManagerFindAllServiceBlockingStub::class)
    fun listarChaveClientMock() = Mockito.mock(KeyManagerFindAllServiceBlockingStub::class.java)

    @field:Inject
    lateinit var listarChaveClient: KeyManagerFindAllServiceBlockingStub

    @field:Inject
    @field:Client("/api/pix")
    lateinit var pixClient: HttpClient

    @Test
    fun `Deve retornar uma lista de PIX`() {
        val clienteId = "5260263c-a3c1-4727-ae32-3bdb2538841b"
        val request = KeyManagerFindAllRequest.newBuilder().setClienteId(clienteId).build()

        Mockito.`when`(listarChaveClient.findAll(request)).thenReturn(KeyManagerFindAllReply.newBuilder()
            .setClienteId(clienteId)
            .addChaves(KeyManagerFindAllReply.ListaDeChaves.newBuilder()
                .setPixId("Okk")
                .setKeyValue("Ok")
                .setCreated("sdfsd")
                .setConta(KeyManagerFindAllReply.Account.CONTA_CORRENTE)
                .setKeyType(KeyManagerFindAllReply.Key.CPF)
                .build()).build())

        val result = pixClient.toBlocking().exchange(HttpRequest.GET<ListarChaveResponse>("/listar/$clienteId"), ListarChaveResponse::class.java)

        Assertions.assertEquals(clienteId, result?.body()?.clientID)
        Assertions.assertTrue(result?.body()?.keyList!!.isNotEmpty())
        Assertions.assertEquals(200, result.status.code)
    }
}