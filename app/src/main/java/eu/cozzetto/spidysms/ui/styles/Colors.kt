package eu.cozzetto.spidysms.ui.styles

import android.graphics.Color

val eu.cozzetto.spidysms.domain.ProcessingState.color: Int
    get() = when (this) {
        eu.cozzetto.spidysms.domain.ProcessingState.Pending -> Color.parseColor("#FFBB86FC")
        eu.cozzetto.spidysms.domain.ProcessingState.Cancelling -> Color.parseColor("#FFFFC107")
        eu.cozzetto.spidysms.domain.ProcessingState.Cancelled -> Color.parseColor("#FF9E9E9E")
        eu.cozzetto.spidysms.domain.ProcessingState.Processed -> Color.parseColor("#FF6200EE")
        eu.cozzetto.spidysms.domain.ProcessingState.Sent -> Color.parseColor("#FF3700B3")
        eu.cozzetto.spidysms.domain.ProcessingState.Delivered -> Color.parseColor("#FF03DAC5")
        eu.cozzetto.spidysms.domain.ProcessingState.Failed -> Color.parseColor("#FF018786")
    }