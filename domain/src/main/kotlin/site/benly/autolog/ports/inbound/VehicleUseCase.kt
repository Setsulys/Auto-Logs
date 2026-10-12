package site.benly.autolog.ports.inbound

import site.benly.autolog.models.Vehicle
import java.util.*

interface VehicleUseCase {

    fun addMileage(vehicleId: UUID, mileage: Int)
    fun addNewVehicle(newVehicle: Vehicle)
    fun findByUser(userId: UUID): List<Vehicle>
    fun removeVehicle(vehicleId: UUID) : Boolean
}