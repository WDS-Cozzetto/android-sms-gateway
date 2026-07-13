package eu.cozzetto.spidysms.modules.logs.repositories

import androidx.lifecycle.distinctUntilChanged
import eu.cozzetto.spidysms.modules.logs.db.LogEntriesDao

class LogsRepository(
    private val dao: LogEntriesDao
) {
    val lastEntries = dao.selectLast().distinctUntilChanged()
}