package site.benly.autolog.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

class VehicleTest {


    companion object {
        @JvmStatic
        fun `invalid vehicle provider`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(-50,1_000,32), //Odometer not negative
                Arguments.of(-50,-50,32),
                Arguments.of(0,-50,1),
                Arguments.of(70_000,10_000,32),// current is higher than last
                Arguments.of(50_000,60_000,0), //tank is not strict positive
            )
        }
    }

    @ParameterizedTest
    @MethodSource("invalid vehicle provider")
    fun `should throw exception when there is a wrong value`(lastOdometer:Int,currentOdometer:Int,tankSize:Int) {
        assertThrows<IllegalArgumentException>{
            Vehicle(
                id = UUID.randomUUID(),
                lastOdometer = lastOdometer,
                currentOdometer = currentOdometer,
                tankSize = tankSize
            )
        }
    }



    @Test
    fun addFuelEntry() {
    }

    @Test
    fun updateOdometer() {
    }


}