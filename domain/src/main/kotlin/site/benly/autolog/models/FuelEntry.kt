package site.benly.autolog.models

import java.time.Instant
import java.util.UUID

data class FuelEntry(
    val id: UUID,
    val vehicleId: UUID,
    val entryDate: Instant,
    val odometerKm: Int,
    val fuelLiters: Double,
    val totalCost: Double,
    val isFullTank: Boolean
){
    init {
        require(entryDate.isBefore(Instant.now()) || entryDate == Instant.now()){
            "Entry date can't be after now"
        }
        require(odometerKm >= 0) { "odometerKm can't be negative" }
        require(fuelLiters > 0) { "fuelLiters can't be negative or equal to 0" }
        require(totalCost >= 0) { "totalCost can't be negative" }
    }

    fun `price per liter`(): Double {
        return totalCost/ fuelLiters
    }
}