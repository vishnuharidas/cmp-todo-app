import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.getDatabaseBuilder

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Screen(
            getDatabaseBuilder().build()
        )
    }
}