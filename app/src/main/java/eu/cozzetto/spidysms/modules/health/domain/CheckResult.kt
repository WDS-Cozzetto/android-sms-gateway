package eu.cozzetto.spidysms.modules.health.domain

data class CheckResult(
    val status: Status,
    val observedValue: Long,
    val observedUnit: String,
    val description: String,
)
