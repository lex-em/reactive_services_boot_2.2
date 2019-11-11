/*
 * Copyright (c) 2019, ReliableTech.ru. All rights reserved.
 */
package ru.reliabletech.java_chel.database_service.config

import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import ru.reliabletech.java_chel.database_service.CommonDatabaseCreator
import ru.reliabletech.java_chel.database_service.repository.TestDataRepository
import java.time.Duration.ofMinutes
import java.time.Duration.ofSeconds
import javax.annotation.PreDestroy

/**
 * @author Emelyanov Alexandr <mr.lex91@gmail.com>
 *         Created on 11.11.2019
 */

@EnableR2dbcRepositories(basePackageClasses = [TestDataRepository::class])
@Configuration
class DbConfig : AbstractR2dbcConfiguration() {

    private var pool: ConnectionPool? = null

    @Bean
    override fun connectionFactory() : ConnectionPool {
        val connectionFactory = PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .username("postgres")
                        .password("postgres")
                        .database("reactive_services")
                        .build())

        val configuration = ConnectionPoolConfiguration.builder(connectionFactory)
                .validationQuery("SELECT 1")
                .maxIdleTime(ofSeconds(2))
                .initialSize(100)
                .maxSize(400)
                .build()

        pool = ConnectionPool(configuration)
        return pool!!
    }

//    @Bean
//    fun client(pool: ConnectionPool): DatabaseClient {
//        return DatabaseClient.create(pool())
//    }

    @PreDestroy
    fun destroy() {
        pool?.let { if (!it.isDisposed) it.dispose() }
    }
//    override fun connectionFactory(): ConnectionFactory {
//        return PostgresqlConnectionFactory(
//                PostgresqlConnectionConfiguration.builder()
//                        .host("localhost")
//                        .port(5432)
//                        .username("postgres")
//                        .password("postgres")
//                        .database("reactive_services")
//                        .build())
//    }
    @Bean
    fun beforeMigrationStrategy(databaseCreator: CommonDatabaseCreator): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway ->
            databaseCreator.createDatabase()
            flyway.migrate()
        }
    }
}