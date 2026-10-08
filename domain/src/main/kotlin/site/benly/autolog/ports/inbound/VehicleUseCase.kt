package site.benly.autolog.ports.inbound

import java.util.*

interface VehicleUseCase {

    fun addMileage(vehicleId: UUID, mileage: Int)
}