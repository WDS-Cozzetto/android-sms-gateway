package eu.cozzetto.spidysms.modules.incoming

import eu.cozzetto.spidysms.modules.incoming.repositories.IncomingMessagesRepository
import eu.cozzetto.spidysms.modules.incoming.vm.IncomingMessagesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val incomingModule = module {
    singleOf(::IncomingMessagesRepository)
    singleOf(::IncomingMessagesService)
    viewModelOf(::IncomingMessagesListViewModel)
}

const val MODULE_NAME = "incoming"
