package site.benly.autolog.model

import java.time.Instant
import java.util.UUID

data class FuelEntry(
    val id: UUID,
    val vehicleId: UUID,
    val entryDate: Instant,
    val odometerKm: Int,
    val fuelLiters: Double,
    val totalCost: Double
)