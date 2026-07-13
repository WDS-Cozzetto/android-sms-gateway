package eu.cozzetto.spidysms.modules.events

import org.koin.dsl.module

val eventBusModule = module {
    single { EventBus() }
}