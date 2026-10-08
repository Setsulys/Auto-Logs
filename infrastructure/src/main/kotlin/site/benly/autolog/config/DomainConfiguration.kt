package site.benly.autolog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient
import site.benly.autolog.adapters.outbound.repository.JdbcVehicleRepositoryAdapter
import site.benly.autolog.ports.inbound.VehicleUseCase
import site.benly.autolog.ports.outbound.VehicleRepositoryPort
import site.benly.autolog.services.VehicleUseCaseImpl


@Configuration
class DomainConfiguration{

    @Bean
    fun vehicleRepositoryPort(jdbcClient: JdbcClient): VehicleRepositoryPort{
        return JdbcVehicleRepositoryAdapter(jdbcClient)
    }

    @Bean
    fun vehicleUseCaseImpl(
        vehicleRepositoryPort: VehicleRepositoryPort
    ): VehicleUseCase{
        return VehicleUseCaseImpl(vehicleRepositoryPort)
    }
}