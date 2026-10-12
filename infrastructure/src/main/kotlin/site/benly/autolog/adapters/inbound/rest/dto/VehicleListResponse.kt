package site.benly.autolog.adapters.inbound.rest.dto

import java.util.*

data class VehicleListResponse(
    val id: UUID,
    val ownerID: UUID,
    val odometer: Int,
    val lastRange: Int,
    val tankSize: Int,
)
