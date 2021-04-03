package br.com.poc

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import javax.inject.Inject


@Testcontainers
@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class ApplicationIT {

    private val logger = KotlinLogging.logger {}

    companion object {
        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:12").apply {
            withDatabaseName("postgres")
            withUsername("postgres")
            withPassword("pwd123")
            start()
            System.setProperty("datasources.default.url", this.jdbcUrl)
            System.setProperty("datasources.default.username", this.username)
            System.setProperty("datasources.default.password", this.password)
            System.setProperty("datasources.default.driverClassName", this.driverClassName)
        }

        @Container
        val kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.1.0")).apply {
            withEmbeddedZookeeper()
            start()
            System.setProperty("kafka.bootstrap.servers", this.bootstrapServers)
            System.setProperty("kafka.schema.registry.url", "mock://localhost:8081")
        }
    }

    @Inject
    lateinit var personClient: PersonGrpcKt.PersonCoroutineStub

    @Test
    fun `should call endpoint`() = runBlocking {
        logger.info { "Kafka Docker is running - ${kafka.isRunning}" }
        Assertions.assertEquals(
            "Person created!",
            personClient.createPerson(
                PersonRequest.newBuilder()
                    .setCpf(12345678901L)
                    .setName("Alison")
                    .build()
            ).message
        )
    }
}