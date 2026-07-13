package eu.cozzetto.spidysms.modules.receiver

import eu.cozzetto.spidysms.modules.settings.KeyValueStorage
import eu.cozzetto.spidysms.modules.settings.get

class StateStorage(
    private val storage: KeyValueStorage,
) {
    var mmsLastProcessedID: Long
        get() = storage.get<Long>(MMS_LAST_PROCESSED_ID) ?: 0
        set(value) = storage.set(MMS_LAST_PROCESSED_ID, value)

    var smsLastProcessedID: Long
        get() = storage.get<Long>(SMS_LAST_PROCESSED_ID) ?: 0
        set(value) = storage.set(SMS_LAST_PROCESSED_ID, value)

    companion object {
        private val PREFIX = "state."

        private val MMS_LAST_PROCESSED_ID = PREFIX + "last_processed_id"
        private val SMS_LAST_PROCESSED_ID = PREFIX + "sms_last_processed_id"
    }
}