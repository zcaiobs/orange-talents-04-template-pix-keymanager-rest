package br.com.orange

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import br.com.orange.KeyManagerRemoveServiceGrpc.*
import br.com.orange.domain.RemoveChaveResponse
import br.com.orange.domain.RemoverChaveRequest
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import org.junit.jupiter.api.Assertions
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest
class RemoverChavePixTest{

    @Singleton
    @Replaces(KeyManagerRemoveServiceBlockingStub::class)
    fun RemoverChaveClientMock() = Mockito.mock(KeyManagerRemoveServiceBlockingStub::class.java)

    @field:Inject
    lateinit var removeChaveClient: KeyManagerRemoveServiceBlockingStub

    @field:Inject
    @field:Client("/api/pix")
    lateinit var pixClient: HttpClient

    @Test
    fun `Deve remover uma chave PIX`() {
        val clienteId = "0d1bb194-3c52-4e67-8c35-a93c0af9284f"
        val pixId = "0e37f720-10b9-4534-b88e-a7f77e010cb6"
        val request = RemoverChaveRequest(clienteId, pixId)
        Mockito.`when`(removeChaveClient.remove(request.toGrpc()))
            .thenReturn(KeyManagerRemoveReply.newBuilder().setDeletedAt("2017-01-13T17:09:42.411").build())

        val result = pixClient.toBlocking().exchange(HttpRequest.DELETE("/", request), RemoveChaveResponse::class.java)

        Assertions.assertEquals("2017-01-13T17:09:42.411", result?.body()?.deletedAt)
    }
}