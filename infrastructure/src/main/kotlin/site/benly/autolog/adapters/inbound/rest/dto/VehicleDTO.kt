package site.benly.autolog.adapters.inbound.rest.dto

import site.benly.autolog.model.Vehicle

data class VehicleDTO(val odometer: Int, val tankSize: Int)

fun VehicleDTO.toVehicle(): Vehicle {
    return Vehicle(currentOdometer = this.odometer, lastOdometer = this.odometer, tankSize = this.tankSize)
}