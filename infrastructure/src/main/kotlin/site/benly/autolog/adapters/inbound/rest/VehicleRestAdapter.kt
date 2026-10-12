package site.benly.autolog.adapters.inbound.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import site.benly.autolog.adapters.inbound.rest.dto.VehicleDTO
import site.benly.autolog.adapters.inbound.rest.dto.VehicleListResponse
import site.benly.autolog.adapters.inbound.rest.dto.toVehicle
import site.benly.autolog.models.exception.VehicleNotFoundException
import site.benly.autolog.ports.inbound.VehicleUseCase
import java.util.*

@RestController
@RequestMapping("/{userId}/vehicles")
class VehicleRestAdapter(
    val vehicleUseCase: VehicleUseCase
){
    @PostMapping("/{vehicleID}/changeMileage")
    fun `add mileage when webclient asked to`(@PathVariable vehicleId: UUID, mileage: Int){
        vehicleUseCase.`add mileage for user vehicle`(vehicleId, mileage)
    }

    @PostMapping("/addVehicle")
    fun `add new vehicle when webclient asked to`(odometer: Int, tankSize: Int, userId: UUID){
        vehicleUseCase.`adding a new vehicle to the user vehicle list`(VehicleDTO(odometer, tankSize, userId).toVehicle())
    }

    @GetMapping("/{vehicleID}")
    fun `get vehicle by id when webclient asked to`(@PathVariable vehicleId: UUID) : ResponseEntity<Any>{
        return try{
            ResponseEntity.ok(vehicleUseCase.`get user vehicle by it id`(vehicleId))
        } catch(e: VehicleNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to (e.message ?: "Vehicle not found")))
        }

    }

    @DeleteMapping("/{vehicleID}")
    fun `remove vehicle when webclient asked to`(@PathVariable vehicleId: UUID): ResponseEntity<Unit> {
        val deleted = vehicleUseCase.`remove vehicle for a user`(vehicleId)
        return if(deleted){
            ResponseEntity.noContent().build()
        }else{
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/list")
    fun `get list of all vehicles of an user`(@RequestParam userId: UUID): List<VehicleListResponse> {
        val vehicles = vehicleUseCase.`find user vehicles list`(userId)
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
