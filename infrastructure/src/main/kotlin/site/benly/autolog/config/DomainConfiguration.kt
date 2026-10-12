package site.benly.autolog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient
import site.benly.autolog.adapters.outbound.repository.JdbcUserRepositoryAdapter
import site.benly.autolog.adapters.outbound.repository.JdbcVehicleRepositoryAdapter
import site.benly.autolog.port.inbound.VehicleUseCase
import site.benly.autolog.port.outbound.UserRepositoryPort
import site.benly.autolog.port.outbound.VehicleRepositoryPort
import site.benly.autolog.service.VehicleUseCaseImpl


@Configuration
class DomainConfiguration{

    @Bean
    fun vehicleRepositoryPort(jdbcClient: JdbcClient): VehicleRepositoryPort{
        return JdbcVehicleRepositoryAdapter(jdbcClient)
    }

    @Bean
    fun userRepositoryPort(jdbcClient: JdbcClient): UserRepositoryPort {
        return JdbcUserRepositoryAdapter(jdbcClient)
    }

    @Bean
    fun vehicleUseCaseImpl(
        vehicleRepositoryPort: VehicleRepositoryPort
    ): VehicleUseCase{
        return VehicleUseCaseImpl(vehicleRepositoryPort)
    }
}