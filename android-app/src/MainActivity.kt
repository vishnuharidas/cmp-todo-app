package hello.world

import Screen
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import data.getDatabaseBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent {
            getDatabaseBuilder(this)
            Screen(
                appDatabase = getDatabaseBuilder(this).build()
            )
        }
    }
}