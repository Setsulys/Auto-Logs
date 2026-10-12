package site.benly.autolog.models.exception

import java.util.*

class VehicleNotFoundException(
    vehicleId : UUID,
): RuntimeException("User not found with id: $vehicleId")