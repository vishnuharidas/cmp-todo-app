package ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import currentTimeInMillis
import data.db.AppDatabase

@Composable
fun HomeScreen(
    appDatabase: AppDatabase,
    viewModel: HomeViewModel = viewModel { HomeViewModel(appDatabase) }
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                val time = currentTimeInMillis()
                viewModel.addTodoItem("ToDo Item (ID: #${time})", "Added on $time")
            }) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(uiState.items) { item ->
                ListItem(
                    headlineContent = { Text(item.title) },
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
