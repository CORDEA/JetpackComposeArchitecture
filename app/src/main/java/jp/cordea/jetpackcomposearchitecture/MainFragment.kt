package jp.cordea.jetpackcomposearchitecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.fragment.app.Fragment
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainFragment : Fragment(R.layout.empty_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        (view as ViewGroup).setContent { View() }
        return view
    }

    @Composable
    private fun View() {
        MaterialTheme {
            Column {
                TopAppBar(title = { Text(text = "Main") })
                FlexColumn(
                    modifier = Spacing(top = 40.dp, left = 16.dp, right = 16.dp),
                    crossAxisSize = SizeMode.Expand,
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
    private fun MainButton(text: String) {
        Button(
            text = text,
            modifier = Spacing(bottom = 16.dp),
            onClick = {
            }
        )
    }

    @Preview
    @Composable
    private fun DefaultPreview() {
        View()
    }
}