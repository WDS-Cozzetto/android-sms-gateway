package eu.cozzetto.spidysms.providers

interface IPProvider {
    suspend fun getIP(): String?
}