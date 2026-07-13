package eu.cozzetto.spidysms.modules.encryption

import org.koin.dsl.module

val encryptionModule = module {
    single {
        EncryptionService(get())
    }
}