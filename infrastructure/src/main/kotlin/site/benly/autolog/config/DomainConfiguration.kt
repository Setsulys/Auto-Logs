
@Configuration
class DomainConfiguration{

    @Bean
    fun recordFuelEntryUseCase(
        fuelEntryRepositoryPort: FuelEntryRepositoryPort
    ): RecordFuelEntryUseCase {
        return FuelTrackingServiceImpl(fuelEntryRepositoryPort)
    }
}