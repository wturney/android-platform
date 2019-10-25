package com.wtl.base.ui

enum class ResourceStatus {
    LOADING,
    ERROR,
    IDLE
}

enum class ResourceError {
    UNKNOWN
}

data class Resource<out ResourceType, out ErrorType>(
    val status: ResourceStatus = ResourceStatus.IDLE,
    val data: ResourceType? = null,
    val error: ErrorType? = null
)
