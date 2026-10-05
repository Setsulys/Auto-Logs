package site.benly.autolog.model

data class Fuel (
    val name: String,
    val literprice: Float
)

enum class FuelType(val value: String) {
    GAZOLE("Gazole"),
    SP_95("Super Sans Plomb 95"),
    E85("Super Ethanol E85"),
    GPLC("GPLc"),
    SP_95_E10("Super Sans Plomb 95 E10"),
    SP_98("Super Sans Plomb 98")
}
