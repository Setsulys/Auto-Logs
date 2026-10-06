package site.benly.autolog.service

import site.benly.autolog.port.inbound.VehicleUseCase
import site.benly.autolog.port.outbound.VehicleRepositoryPort
import java.util.*


class VehicleUseCaseImpl(
    private val vehicleRepository: VehicleRepositoryPort
) : VehicleUseCase{
    override fun execute(vehicleId: UUID, mileage: Int) {
        val vehicle = vehicleRepository.findById(vehicleId) ?: throw IllegalStateException("Could not find vehicle with id $vehicleId")
        vehicle.updateOdometer(mileage)
        vehicleRepository.save(vehicle)
    }
}
