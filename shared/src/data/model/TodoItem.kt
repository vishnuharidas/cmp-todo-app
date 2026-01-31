package data.model

data class TodoItem(
    val id: Long,
    val text: String,
    val isCompleted: Boolean = false
)