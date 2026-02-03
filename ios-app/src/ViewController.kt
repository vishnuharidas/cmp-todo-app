import androidx.compose.ui.window.ComposeUIViewController
import data.getDatabaseBuilder

fun ViewController() = ComposeUIViewController {
    Screen(
        getDatabaseBuilder().build()
    )
}