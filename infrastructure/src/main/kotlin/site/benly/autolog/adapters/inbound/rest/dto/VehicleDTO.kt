package site.benly.autolog.adapters.inbound.rest.dto

import site.benly.autolog.models.Vehicle
import java.util.*

data class VehicleDTO(val odometer: Int, val tankSize: Int, val userId: UUID)

fun VehicleDTO.toVehicle(): Vehicle {
    return Vehicle(odometer = this.odometer, tankSize = this.tankSize, userId = this.userId)
}