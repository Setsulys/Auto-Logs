package site.benly.autolog.port.inbound

import site.benly.autolog.model.Vehicle
import java.util.*

interface VehicleUseCase {

    fun addMileage(vehicleId: UUID, mileage: Int)
    fun addNewVehicle(newVehicle: Vehicle)
    fun findByOwner(ownerID: UUID): List<Vehicle>
}