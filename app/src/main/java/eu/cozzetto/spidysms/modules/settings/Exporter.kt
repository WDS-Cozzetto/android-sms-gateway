package eu.cozzetto.spidysms.modules.settings

interface Exporter {
    fun export(): Map<String, *>
}