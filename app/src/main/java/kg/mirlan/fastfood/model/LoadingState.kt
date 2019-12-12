package kg.mirlan.fastfood.model

data class LoadingState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADING = LoadingState(Status.RUNNING)
        val LOADED = LoadingState(Status.SUCCESS)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
}