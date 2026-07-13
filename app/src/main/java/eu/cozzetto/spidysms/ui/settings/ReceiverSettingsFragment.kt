package eu.cozzetto.spidysms.ui.settings

import android.os.Bundle
import eu.cozzetto.spidysms.R

class ReceiverSettingsFragment : BasePreferenceFragment() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.receiver_preferences, rootKey)
    }
}
