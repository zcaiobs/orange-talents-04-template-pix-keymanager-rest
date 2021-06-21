package br.com.orange

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import br.com.orange.KeyManagerFindServiceGrpc.*
import br.com.orange.domain.ConsultaChaveResponse
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import org.junit.jupiter.api.Assertions
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest
class ConsultaChavePixTest {

    @Singleton
    @Replaces(KeyManagerFindServiceBlockingStub::class)
    fun consultaChaveClientMock() = Mockito.mock(KeyManagerFindServiceBlockingStub::class.java)

    @field:Inject
    lateinit var consultarChaveClient: KeyManagerFindServiceBlockingStub

    @field:Inject
    @field:Client("/api/pix")
    lateinit var pixClient: HttpClient

    @Test
    fun `Deve retornar as informacoes da chave PIX`() {
        val clienteId = "5260263c-a3c1-4727-ae32-3bdb2538841b"
        val pixId = "de8aab0b-c887-4805-bd49-0fc23476a686"
        val request = KeyManagerFindRequest.newBuilder()
            .setKeymanagerFind(KeyManagerFindRequest.KeyManagerFind.newBuilder()
            .setClientId(clienteId).setIdPix(pixId).build()).build()

        Mockito.`when`(consultarChaveClient.find(request)).thenReturn(
            KeyManagerFindReply.newBuilder()
                .setClientId("5260263c-a3c1-4727-ae32-3bdb2538841b")
                .setIdPix("de8aab0b-c887-4805-bd49-0fc23476a686")
                .setKeyType(KeyManagerFindReply.Key.PHONE)
                .setKeyValue("+55789098768")
                .setTitular(KeyManagerFindReply.Titular.newBuilder().setCpf("86135457004").setName("Yuri Matheus").build())
                .setConta(KeyManagerFindReply.InstituicaoFinanceira.newBuilder()
                    .setNome("ITAÚ UNIBANCO S.A.")
                    .setConta("291900")
                    .setAgencia("0001")
                    .setTipo(KeyManagerFindReply.InstituicaoFinanceira.Account.CONTA_POUPANCA).build())
                .setCriadoEm("2021-06-15T12:17:13.999229").build()
        )

        val result = pixClient.toBlocking()
            .exchange(HttpRequest.GET<ConsultaChaveResponse>("/$clienteId?pixId=$pixId"),
                ConsultaChaveResponse::class.java)

        Assertions.assertNotNull(result.body())
        Assertions.assertNotNull(result?.body()?.clientId)
        Assertions.assertEquals(clienteId, result?.body()?.clientId)
    }
}