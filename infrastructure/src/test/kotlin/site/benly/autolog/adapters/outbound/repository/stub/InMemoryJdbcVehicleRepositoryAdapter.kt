package site.benly.autolog.adapters.outbound.repository.stub

import site.benly.autolog.model.Vehicle
import site.benly.autolog.port.outbound.VehicleRepositoryPort
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class InMemoryJdbcVehicleRepositoryAdapter : VehicleRepositoryPort{

    private val database = ConcurrentHashMap<UUID, Vehicle>()

    override fun findById(id: UUID): Vehicle? {
        return database[id]
    }

    override fun save(vehicle: Vehicle) {
        database[vehicle.id] = vehicle
    }

    fun clear(){
        database.clear()
    }
}