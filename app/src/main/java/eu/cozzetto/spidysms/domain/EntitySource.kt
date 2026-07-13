package eu.cozzetto.spidysms.domain

enum class EntitySource {
    Local,
    Cloud,

    @Deprecated("Not used anymore")
    Gateway,
}