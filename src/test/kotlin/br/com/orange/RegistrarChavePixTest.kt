package br.com.orange

import br.com.orange.KeyManagerRegisterServiceGrpc.*
import br.com.orange.domain.RegistrarChaveRequest
import br.com.orange.domain.RegistrarChaveResponse
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest
class RegistrarChavePixTest() {

    @Singleton
    @Replaces(bean = KeyManagerRegisterServiceBlockingStub::class)
    fun registraChaveClientMock() = Mockito.mock(KeyManagerRegisterServiceBlockingStub::class.java)

    @field:Inject
    lateinit var registraChaveClient: KeyManagerRegisterServiceBlockingStub

    @field:Inject
    @field:Client("/api/pix")
    lateinit var pixClient: HttpClient

    @Test
    fun `Deve registrar uma chave PIX`() {
        val clienteId = "5260263c-a3c1-4727-ae32-3bdb2538841b"
        val tipoChave = "PHONE"
        val valorChave = "+5578909872"
        val tipoConta = "CONTA_CORRENTE"
        val request = RegistrarChaveRequest(clienteId, tipoChave, valorChave, tipoConta)

        Mockito.`when`(registraChaveClient.register(request.toGrpc()))
            .thenReturn(KeyManagerReply.newBuilder()
                .setMessage("PIX created successfully")
                .setIdPix("4889e9b2-c20f-4ec3-b359-cf675693e723")
                .build())

        val result = pixClient.toBlocking().exchange(HttpRequest.POST("/", request), RegistrarChaveResponse::class.java)

        assertNotNull(result.body())
        assertEquals("4889e9b2-c20f-4ec3-b359-cf675693e723", result?.body()?.pixId)
    }
}
