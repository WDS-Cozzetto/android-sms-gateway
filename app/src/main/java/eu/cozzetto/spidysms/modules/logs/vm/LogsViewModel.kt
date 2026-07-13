package eu.cozzetto.spidysms.modules.logs.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eu.cozzetto.spidysms.modules.logs.db.LogEntry
import eu.cozzetto.spidysms.modules.logs.repositories.LogsRepository

class LogsViewModel(
    logs: LogsRepository
) : ViewModel() {
    val lastEntries: LiveData<List<LogEntry>> = logs.lastEntries
}