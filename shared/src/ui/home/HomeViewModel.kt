package ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import data.model.TodoItem
import ui.home.HomeViewModel.HomeScreenState.Status

class HomeViewModel : ViewModel() {

    private val _todoItems = mutableListOf<TodoItem>()

    private var _homeScreenState by mutableStateOf<HomeScreenState>(
        HomeScreenState(
            // Initial state
            status = Status.NONE,
            items = _todoItems
        )
    )
    val homeScreenState: HomeScreenState get() = _homeScreenState

    fun addTodoItem(item: TodoItem) {
        _todoItems.add(item)

        // Update state
        _homeScreenState = HomeScreenState(
            status = Status.SUCCESS,
            items = ArrayList(_todoItems)
        )
    }

    fun removeTodoItem(id: Long) {
        _todoItems.removeAll { it.id == id }

        // Update state
        _homeScreenState = HomeScreenState(
            status = Status.SUCCESS,
            items = ArrayList(_todoItems)
        )
    }


    data class HomeScreenState(
        val status: Status = Status.NONE,
        val error: String? = null,
        val items: List<TodoItem>
    ) {
        enum class Status {
            NONE,
            LOADING,
            SUCCESS,
            ERROR
        }
    }

}