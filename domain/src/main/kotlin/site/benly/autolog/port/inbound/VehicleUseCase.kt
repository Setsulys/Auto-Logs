package site.benly.autolog.port.inbound

import site.benly.autolog.port.outbound.VehicleRepositoryPort
import java.util.*


fun `add new mileage to odometer`(vehicleId: UUID,mileage:Int,vehicleRepository:VehicleRepositoryPort){
    val vehicle = vehicleRepository.findById(vehicleId) ?: throw IllegalStateException("Could not find vehicle with id $vehicleId")
    vehicle.updateOdometer(mileage)
    vehicleRepository.save(vehicle)
}