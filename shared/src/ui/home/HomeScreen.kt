package ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.model.TodoItem

@Composable
fun HomeScreen() {
    var items by remember { mutableStateOf(listOf<TodoItem>()) }
    var nextId by remember { mutableStateOf(1L) }

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
                val newItem = TodoItem(
                    id = nextId++,
                    text = "Todo Item #$nextId"
                )
                items = items + newItem
            }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(items) { item ->
                ListItem(
                    headlineContent = { Text(item.text) }
                )
            }
        }
    }
}
