package eu.cozzetto.spidysms.modules.settings

interface Importer {
    fun import(data: Map<String, *>): Boolean
}