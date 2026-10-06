package site.benly.autolog.port.inbound

import java.util.*

interface VehicleUseCase {

    fun execute(vehicleId: UUID, mileage: Int)
}