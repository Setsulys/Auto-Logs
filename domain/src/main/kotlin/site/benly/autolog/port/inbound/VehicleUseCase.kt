package site.benly.autolog.port.inbound

import java.util.*

interface VehicleUseCase {

    fun addMileage(vehicleId: UUID, mileage: Int)
}