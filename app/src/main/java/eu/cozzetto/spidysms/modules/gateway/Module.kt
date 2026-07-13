package eu.cozzetto.spidysms.modules.gateway

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gatewayModule = module {
    singleOf(::GatewayService)
}

val MODULE_NAME = "gateway"
