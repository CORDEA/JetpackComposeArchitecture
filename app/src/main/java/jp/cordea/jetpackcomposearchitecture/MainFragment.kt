package jp.cordea.jetpackcomposearchitecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
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
                Column(
                    modifier = LayoutPadding(top = 40.dp, left = 16.dp, right = 16.dp)
                ) {
                    MainButton(text = "MVVM") {
                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToMvvmFragment()
                        )
                    }
                    MainButton(text = "b") {
                    }
                    MainButton(text = "c") {
                    }
                }
            }
        }
    }

    @Composable
    private fun MainButton(text: String, onClick: () -> Unit) {
        Button(
            text = text,
            modifier = LayoutPadding(bottom = 16.dp) + LayoutWidth.Fill,
            onClick = onClick
        )
    }

    @Preview
    @Composable
    private fun DefaultPreview() {
        View()
    }
}