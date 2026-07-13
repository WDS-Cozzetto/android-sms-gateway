package eu.cozzetto.spidysms.domain

enum class ProcessingState {
    Pending,
    Cancelling,
    Cancelled,
    Processed,
    Sent,
    Delivered,
    Failed
}