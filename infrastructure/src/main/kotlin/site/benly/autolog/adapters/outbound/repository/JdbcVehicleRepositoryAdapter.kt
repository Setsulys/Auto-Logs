package site.benly.autolog.adapters.outbound.repository

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component
import site.benly.autolog.model.Vehicle
import site.benly.autolog.port.outbound.VehicleRepositoryPort
import java.util.*


@Component
class JdbcVehicleRepositoryAdapter(
    val jdbcClient: JdbcClient
): VehicleRepositoryPort {

    override fun findById(id: UUID): Vehicle? {
        val sql = """
             SELECT *
             FROM vehicle
             WHERE id = :id
        """.trimIndent()

        return jdbcClient.sql(sql)
            .param("id", id)
            .query{ rs, _ ->
                Vehicle(
                    id = UUID.fromString(rs.getString("id")),
                    ownerID = UUID.fromString(rs.getString("ownerID")),
                    currentOdometer = rs.getInt("currentOdometer"),
                    lastOdometer = rs.getInt("lastOdometer"),
                    tankSize = rs.getInt("tankSize"),
                )
            }
            .optional()
            .orElse(null)
    }

    override fun save(vehicle: Vehicle) {
       val sql = """
            INSERT INTO vehicle (id, last_odometer, current_odometer,tank_size, ownerID)
            VALUES (:id, :lastOdometer, :currentOdometer, :tankSize, :ownerID)
            ON CONFLICT (id)
            DO UPDATE SET current_odometer = :currentOdometer
            WHERE id = :id
       """.trimIndent()

        jdbcClient.sql(sql)
            .param("id", vehicle.id.toString())
            .param("ownerID", vehicle.ownerID.toString())
            .param("lastOdometer", vehicle.lastOdometer.toString())
            .param("currentOdometer", vehicle.currentOdometer.toString())
            .param("tankSize", vehicle.tankSize.toString())
            .update()
    }

    override fun findByOwner(ownerID: UUID): List<Vehicle> {
        val sql = """
            SELECT *
            FROM vehicle
            WHERE ownerID = :ownerID
        """.trimIndent()
        return jdbcClient.sql(sql)
            .param("ownerID", ownerID)
            .query{rs, _ ->
                Vehicle(
                    id = UUID.fromString(rs.getString("id")),
                    ownerID = UUID.fromString(rs.getString("ownerID")),
                    currentOdometer = rs.getInt("currentOdometer"),
                    lastOdometer = rs.getInt("lastOdometer"),
                    tankSize = rs.getInt("tankSize"),
                )
            }
            .list()
    }
}