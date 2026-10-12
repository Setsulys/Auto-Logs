package site.benly.autolog.model
import java.time.Instant
import java.util.*

class Vehicle(
    val id: UUID = UUID.randomUUID(),
    val odometer: Int = 0,
    val lastRange: Int = 0,
    val tankSize: Int,
    val userId: UUID
){
    init {
        require(odometer >= 0){ "Inital odometer can't be negative" }
        require( lastRange >= 0 ){" Current Odometer can't be negative"}
        require(tankSize > 0){ "Tank size can't be 0 or less" }
    }

    fun addFuelEntry(
        odometerKm: Int,
        userId: UUID,
        fuelLiters: Double,
        totalCost: Double,
        lastKnownOdometer: Int,
        isFullTank: Boolean
    ): FuelEntry{
        require(odometerKm >= lastKnownOdometer){ "Odometer of the filling ($odometerKm km) must be superior to the last filling" }
        require(fuelLiters >= 0){ "The fuel volume must be positive" }
        require(tankSize > fuelLiters){ "Too much fuel for the tank" }
        require(totalCost >= 0){ "Ths cost can't be negative" }

        return FuelEntry(
            id = UUID.randomUUID(),
            vehicleId = this.id,
            entryDate = Instant.now(),
            odometerKm = odometerKm,
            fuelLiters = fuelLiters,
            totalCost = totalCost,
            isFullTank = isFullTank
        )
    }

    fun updateOdometer(mileage:Int):Vehicle{
        val range = mileage-odometer
        return Vehicle(
            id=this.id,
            odometer = odometer,
            lastRange = range,
            tankSize = this.tankSize,
            userId = this.userId
        )
    }
}