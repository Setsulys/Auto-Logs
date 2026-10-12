package site.benly.autolog.ports.outbound

import site.benly.autolog.models.Vehicle
import java.util.*

interface VehicleRepositoryPort {

    fun findById(vehicleId: UUID): Vehicle?

    fun save(vehicle: Vehicle)

    fun findByUser(userId: UUID): List<Vehicle>

    fun delete(vehicleId: UUID) : Boolean
}