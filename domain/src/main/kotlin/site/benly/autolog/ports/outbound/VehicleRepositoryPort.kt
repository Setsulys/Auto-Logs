package site.benly.autolog.ports.outbound

import site.benly.autolog.models.Vehicle
import java.util.*

interface VehicleRepositoryPort {

    fun findById(id: UUID): Vehicle?

    fun save(vehicle: Vehicle)

}