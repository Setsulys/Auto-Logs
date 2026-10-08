package site.benly.autolog.adapters.inbound.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import site.benly.autolog.adapters.inbound.rest.dto.VehicleDTO
import site.benly.autolog.adapters.inbound.rest.dto.VehicleListResponse
import site.benly.autolog.adapters.inbound.rest.dto.toVehicle
import site.benly.autolog.model.Vehicle
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
    fun `add new vehicle when webclient asked to`(odometer: Int, tankSize: Int, owner: String){
        vehicleUseCase.addNewVehicle(VehicleDTO(odometer, tankSize, owner).toVehicle())
    }

    @GetMapping("/vehicles/list")
    fun `get list of all vehicles of an user`(@RequestParam owner: String): List<VehicleListResponse> {
        val vehicles = vehicleUseCase.findByOwner(owner)
        return vehicles.map { vehicle ->
            VehicleListResponse(
                id = vehicle.id,
                currentOdometer = vehicle.currentOdometer,
                lastOdometer = vehicle.lastOdometer,
                tankSize = vehicle.tankSize,
            )
        }
    }
}
