package site.benly.autolog.adapters.inbound.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import site.benly.autolog.adapters.inbound.rest.dto.VehicleDTO
import site.benly.autolog.adapters.inbound.rest.dto.VehicleListResponse
import site.benly.autolog.adapters.inbound.rest.dto.toVehicle
import site.benly.autolog.port.inbound.VehicleUseCase
import java.util.*

@RestController
@RequestMapping("/api/v1/{userId}/vehicles")
class VehicleRestAdapter(
    val vehicleUseCase: VehicleUseCase
){
    @PostMapping("/{vehicleID}/changeMileage")
    fun `add mileage when webclient asked to`(@PathVariable vehicleId: UUID, mileage: Int){
        vehicleUseCase.addMileage(vehicleId, mileage)
    }

    @PostMapping("/addVehicle")
    fun `add new vehicle when webclient asked to`(odometer: Int, tankSize: Int, owner: String){
        vehicleUseCase.addNewVehicle(VehicleDTO(odometer, tankSize, owner).toVehicle())
    }

    @DeleteMapping("/{vehicleID}")
    fun `remove vehicle when webclient asked to`(@PathVariable vehicleId: UUID): ResponseEntity<Unit> {
        val deleted = vehicleUseCase.removeVehicle(vehicleId)
        return if(deleted){
            ResponseEntity.noContent().build()
        }else{
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/list")
    fun `get list of all vehicles of an user`(@RequestParam userId: UUID): List<VehicleListResponse> {
        val vehicles = vehicleUseCase.findByUser(userId)
        return vehicles.map { vehicle ->
            VehicleListResponse(
                id = vehicle.id,
                userId = vehicle.userId,
                odometer = vehicle.odometer,
                lastRange = vehicle.lastRange,
                tankSize = vehicle.tankSize,
            )
        }
    }
}
