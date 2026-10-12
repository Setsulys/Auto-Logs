package site.benly.autolog.ports.inbound

import site.benly.autolog.models.Vehicle
import java.util.*

interface VehicleUseCase {


    fun `get user vehicle by it id`(vehicleId: UUID): Vehicle?
    fun `add mileage for user vehicle`(vehicleId: UUID, mileage: Int)
    fun `adding a new vehicle to the user vehicle list`(newVehicle: Vehicle)
    fun `find user vehicles list`(userId: UUID): List<Vehicle>
    fun `remove vehicle for a user`(vehicleId: UUID) : Boolean
}