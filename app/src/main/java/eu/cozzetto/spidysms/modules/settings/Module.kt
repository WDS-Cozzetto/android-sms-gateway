package eu.cozzetto.spidysms.modules.settings

import androidx.preference.PreferenceManager
import eu.cozzetto.spidysms.helpers.SettingsHelper
import eu.cozzetto.spidysms.modules.encryption.EncryptionSettings
import eu.cozzetto.spidysms.modules.gateway.GatewaySettings
import eu.cozzetto.spidysms.modules.localserver.LocalServerSettings
import eu.cozzetto.spidysms.modules.logs.LogsSettings
import eu.cozzetto.spidysms.modules.messages.MessagesSettings
import eu.cozzetto.spidysms.modules.ping.PingSettings
import eu.cozzetto.spidysms.modules.receiver.ReceiverSettings
import eu.cozzetto.spidysms.modules.receiver.StateStorage
import eu.cozzetto.spidysms.modules.webhooks.TemporaryStorage
import eu.cozzetto.spidysms.modules.webhooks.WebhooksSettings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val settingsModule = module {
    singleOf(::SettingsService)
    factory { PreferenceManager.getDefaultSharedPreferences(get()) }
    factory { SettingsHelper(get()) }

    factory {
        EncryptionSettings(
            PreferencesStorage(get(), "encryption")
        )
    }
    factory {
        GatewaySettings(
            PreferencesStorage(get(), "gateway")
        )
    }
    factory {
        MessagesSettings(
            PreferencesStorage(get(), "messages")
        )
    }
    factory {
        LocalServerSettings(
            PreferencesStorage(get(), "localserver")
        )
    }
    factory {
        PingSettings(
            PreferencesStorage(get(), "ping")
        )
    }
    factory {
        LogsSettings(
            PreferencesStorage(get(), "logs")
        )
    }
    factory {
        WebhooksSettings(
            PreferencesStorage(get(), "webhooks")
        )
    }
    single {
        TemporaryStorage(
            PreferencesStorage(get(), "webhooks")
        )
    }
    factory {
        ReceiverSettings(
            PreferencesStorage(get(), "receiver")
        )
    }
    single {
        StateStorage(
            PreferencesStorage(get(), "receiver")
        )
    }
}