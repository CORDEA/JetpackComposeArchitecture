package jp.cordea.jetpackcomposearchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            View()
        }
    }
}

@Composable
fun View() {
    MaterialTheme {
        Column {
            TopAppBar(title = { Text(text = "Main") })
            FlexColumn(
                modifier = Spacing(top = 40.dp, left = 16.dp, right = 16.dp),
                crossAxisSize = LayoutSize.Expand,
                crossAxisAlignment = CrossAxisAlignment.Stretch,
                mainAxisAlignment = MainAxisAlignment.Center
            ) {
                inflexible {
                    MainButton(text = "a")
                    MainButton(text = "b")
                    MainButton(text = "c")
                }
            }
        }
    }
}

@Composable
fun MainButton(text: String) {
    Button(
        text = text,
        modifier = Spacing(bottom = 16.dp),
        onClick = {
        }
    )
}

@Preview
@Composable
fun DefaultPreview() {
    View()
}