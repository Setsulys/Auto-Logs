package site.benly.autolog.port.outbound

import site.benly.autolog.model.Vehicle
import java.util.*

interface VehicleRepositoryPort {

    fun findById(id: UUID): Vehicle?

    fun save(vehicle: Vehicle)

    fun findByOwner(owner: UUID): List<Vehicle>

}