import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import data.db.AppDatabase
import ui.home.HomeScreen

@Composable
fun Screen(appDatabase: AppDatabase) {
    MaterialTheme {
        HomeScreen(appDatabase)
    }
}
