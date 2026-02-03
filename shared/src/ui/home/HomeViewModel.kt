package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.db.AppDatabase
import data.db.TodoEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    val database: AppDatabase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val uiState: StateFlow<HomeScreenState> = combine(
        _isLoading,
        database.getDao()
            .getAllAsFlow()
            .distinctUntilChanged()
            .catch { emit(emptyList()) },
    ) { loading, items ->

        HomeScreenState(
            isLoading = loading,
            items = items,
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeScreenState(isLoading = false, items = emptyList())
    )

    fun addTodoItem(title: String, content: String) {

        viewModelScope.launch {

            _isLoading.value = true
            val newItem = TodoEntity(title = title, content = content)

            try {
                database.getDao().insert(newItem)
            } finally {
                _isLoading.value = false
            }
        }

    }

    fun removeTodoItem(id: Long) {
        // TODO
    }


    // State is not mutually exclusive here (
    data class HomeScreenState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val items: List<TodoEntity>
    )

}