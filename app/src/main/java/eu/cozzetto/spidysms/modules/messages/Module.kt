package eu.cozzetto.spidysms.modules.messages

import eu.cozzetto.spidysms.modules.messages.vm.MessageDetailsViewModel
import eu.cozzetto.spidysms.modules.messages.vm.MessagesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val messagesModule = module {
    single { MessagesRepository(get()) }
    singleOf(::MessagesService)
    viewModel { MessagesListViewModel(get()) }
    viewModel { MessageDetailsViewModel(get()) }
}

val MODULE_NAME = "messages"
