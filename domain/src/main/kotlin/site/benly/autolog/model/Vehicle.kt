package site.benly.autolog.model
import java.time.Instant
import java.util.*

class Vehicle(
    val id: UUID,
    val lastOdometer: Int,
    val currentOdometer: Int,
    val tankSize: Int
){
    init {
        require(lastOdometer >= 0){ "Inital odometer can't be negative" }
        require( currentOdometer >= 0 ){" Current Odometer can't be negative"}
        require( currentOdometer >= lastOdometer){" Current Odometer can't be inferior to the last odometer"}
        require(tankSize > 0){ "Tank size can't be negative" }
    }

    fun addFuelEntry(
        odometerKm: Int,
        fuelLiters: Double,
        totalCost: Double,
        lastKnownOdometer: Int
    ): FuelEntry{
        require(odometerKm > lastKnownOdometer){ "Odometer of the filling ($odometerKm km) must be superior to the last filling" }
        require(fuelLiters > 0){ "The fuel volume must be positive" }
        require(totalCost >= 0){ "Ths cost can't be negative" }

        return FuelEntry(
            id = UUID.randomUUID(),
            vehicleId = this.id,
            entryDate = Instant.now(),
            odometerKm = odometerKm,
            fuelLiters = fuelLiters,
            totalCost = totalCost
        )
    }

    fun updateOdometer(mileage:Int):Vehicle{
        return Vehicle(
            id=this.id,
            lastOdometer = this.currentOdometer,
            currentOdometer = mileage,
            tankSize = this.tankSize
        )
    }
}