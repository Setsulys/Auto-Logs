package site.benly.autolog.services

import site.benly.autolog.ports.inbound.VehicleUseCase
import site.benly.autolog.ports.outbound.VehicleRepositoryPort
import java.util.*


class VehicleUseCaseImpl(
    private val vehicleRepository: VehicleRepositoryPort
) : VehicleUseCase{

    override fun addMileage(vehicleId: UUID, mileage: Int) {
        val vehicle = vehicleRepository.findById(vehicleId) ?: throw IllegalStateException("Could not find vehicle with id $vehicleId")
        vehicle.updateOdometer(mileage)
        vehicleRepository.save(vehicle)
    }

}
