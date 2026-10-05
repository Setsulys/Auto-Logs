package site.benly.autolog.port.inbound

import Vehicle

class VehicleUseCase {

    fun `add new mileage to odometer`(vehicle:Vehicle,mileage: Int):Vehicle{
        return vehicle.copy(
//            lastOdometer = vehicle.odometer,
//            odometer = mileage
            )
    }
}