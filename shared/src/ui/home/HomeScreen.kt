package ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import currentTimeInMillis
import data.model.TodoItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel { HomeViewModel() }
) {

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("ToDo App") },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.Green,
                    actionIconContentColor = Color.Blue
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val id = currentTimeInMillis()
                val newItem = TodoItem(
                    id = id,
                    text = "Todo Item (ID: #$id)"
                )
                viewModel.addTodoItem(item = newItem)
            }) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(viewModel.homeScreenState.items) { item ->
                ListItem(
                    headlineContent = { Text(item.text) },
                    trailingContent = {
                        IconButton(
                            onClick = { viewModel.removeTodoItem(item.id) },
                        ) {
                            Icon(Icons.Default.Delete, "Delete Item")
                        }
                    }
                )
            }
        }
    }
}
