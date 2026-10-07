package site.benly.autolog.adapters.inbound.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import site.benly.autolog.adapters.inbound.rest.dto.VehicleDTO
import site.benly.autolog.adapters.inbound.rest.dto.toVehicle
import site.benly.autolog.port.inbound.VehicleUseCase
import java.util.UUID

@RestController
class VehicleRestAdapter(
    val vehicleUseCase: VehicleUseCase
){
    @PostMapping("/vehicles/addFuel")
    fun `add mileage when webclient asked to`(vehicleId: UUID, mileage: Int){
        vehicleUseCase.addMileage(vehicleId, mileage)
    }

    @PostMapping("/addVehicle")
    fun `add new vehicle when webclient asked to`(odometer: Int, tankSize: Int){
        vehicleUseCase.addNewVehicle(VehicleDTO(odometer, tankSize).toVehicle())
    }
}
