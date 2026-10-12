package site.benly.autolog.service

import site.benly.autolog.models.Vehicle
import site.benly.autolog.models.exception.VehicleNotFoundException
import site.benly.autolog.ports.inbound.VehicleUseCase
import site.benly.autolog.ports.outbound.VehicleRepositoryPort
import java.util.*


class VehicleUseCaseImpl(
    private val vehicleRepository: VehicleRepositoryPort
) : VehicleUseCase {
    override fun `get user vehicle by it id`(vehicleId: UUID): Vehicle {
        return  vehicleRepository.findById(vehicleId)?:throw VehicleNotFoundException(vehicleId)
    }

    override fun `add mileage for user vehicle`(vehicleId: UUID, mileage: Int) {
        val vehicle = vehicleRepository.findById(vehicleId) ?: throw IllegalStateException("Could not find vehicle with id $vehicleId")
        vehicle.updateOdometer(mileage)
        vehicleRepository.save(vehicle)
    }

    override fun `adding a new vehicle to the user vehicle list`(newVehicle: Vehicle) {
        vehicleRepository.save(newVehicle)
    }

    override fun `find user vehicles list`(userId: UUID): List<Vehicle> {
        val vehicles = vehicleRepository.findByUser(userId)
        return vehicles
    }

    override fun `remove vehicle for a user`(vehicleId: UUID) :Boolean {
        vehicleRepository.findById(vehicleId) ?: throw IllegalStateException("Could not find vehicle with id $vehicleId")
        return vehicleRepository.delete(vehicleId)
    }
}
