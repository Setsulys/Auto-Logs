package site.benly.autolog.adapters.outbound.repository

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component
import site.benly.autolog.models.Vehicle
import site.benly.autolog.ports.outbound.VehicleRepositoryPort
import java.util.*


@Component
class JdbcVehicleRepositoryAdapter(
    val jdbcClient: JdbcClient
): VehicleRepositoryPort {

    override fun findById(vehicleId: UUID): Vehicle? {
        val sql = """
             SELECT *
             FROM vehicle
             WHERE id = :vehicleId
        """.trimIndent()

        return jdbcClient.sql(sql)
            .param("id", vehicleId)
            .query{ rs, _ ->
                Vehicle(
                    id = UUID.fromString(rs.getString("id")),
                    userId = UUID.fromString(rs.getString("userId")),
                    odometer = rs.getInt("odometer"),
                    lastRange = rs.getInt("lastRange"),
                    tankSize = rs.getInt("tankSize"),
                )
            }
            .optional()
            .orElse(null)
    }

    override fun save(vehicle: Vehicle) {
       val sql = """
            INSERT INTO vehicle (id, odometer, lastRange,tank_size, userId)
            VALUES (:id, :odometer, :lastRange, :tankSize, :userId)
            ON CONFLICT (id)
            DO UPDATE SET odometer = :odometer
            WHERE id = :id
       """.trimIndent()

        jdbcClient.sql(sql)
            .param("id", vehicle.id.toString())
            .param("userId", vehicle.userId.toString())
            .param("odometer", vehicle.odometer.toString())
            .param("lastRange", vehicle.lastRange.toString())
            .param("tankSize", vehicle.tankSize.toString())
            .update()
    }

    override fun findByUser(userId: UUID): List<Vehicle> {
        val sql = """
            SELECT *
            FROM vehicle
            WHERE userId = :userId
        """.trimIndent()
        return jdbcClient.sql(sql)
            .param("userId", userId)
            .query{rs, _ ->
                Vehicle(
                    id = UUID.fromString(rs.getString("id")),
                    userId = UUID.fromString(rs.getString("userId")),
                    odometer = rs.getInt("currentOdometer"),
                    lastRange = rs.getInt("lastOdometer"),
                    tankSize = rs.getInt("tankSize"),
                )
            }
            .list()
    }

    override fun delete(vehicleId: UUID): Boolean {
        val sql = """
            DELETE FROM vehicle
            WHERE vehicleId = :vehicleId
        """.trimIndent()
        return jdbcClient.sql(sql).param("vehicleId",vehicleId).update()==1
    }
}