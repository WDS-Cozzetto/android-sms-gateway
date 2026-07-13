package eu.cozzetto.spidysms.domain

import eu.cozzetto.spidysms.BuildConfig
import eu.cozzetto.spidysms.modules.health.domain.CheckResult
import eu.cozzetto.spidysms.modules.health.domain.HealthResult
import eu.cozzetto.spidysms.modules.health.domain.Status

class HealthResponse(
    healthResult: HealthResult,

    val version: String = BuildConfig.VERSION_NAME,
    val releaseId: Int = BuildConfig.VERSION_CODE,
) {
    val status: Status = healthResult.status
    val checks: Map<String, CheckResult> = healthResult.checks
}