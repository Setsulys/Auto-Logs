package site.benly.autolog.adapters.inbound.rest.dto

import java.util.UUID

data class VehicleListResponse(
    val id: UUID,
    val ownerID: UUID,
    val currentOdometer: Int,
    val lastOdometer: Int,
    val tankSize: Int,
)
