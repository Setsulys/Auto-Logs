package site.benly.autolog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient
import site.benly.autolog.adapters.outbound.repository.JdbcVehicleRepositoryAdapter
import site.benly.autolog.port.outbound.VehicleRepositoryPort


@Configuration
class DomainConfiguration{

    @Bean
    fun vehicleRepositoryPort(jdbcClient: JdbcClient): VehicleRepositoryPort{
        return JdbcVehicleRepositoryAdapter(jdbcClient)
    }
}