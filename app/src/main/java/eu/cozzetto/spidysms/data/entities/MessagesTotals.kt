package eu.cozzetto.spidysms.data.entities

data class MessagesTotals(
    val total: Long,
    val pending: Long,
    val cancelling: Long,
    val cancelled: Long,
    val sent: Long,
    val delivered: Long,
    val failed: Long,
)
