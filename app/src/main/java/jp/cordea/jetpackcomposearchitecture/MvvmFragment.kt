package jp.cordea.jetpackcomposearchitecture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MvvmFragment : Fragment(R.layout.empty_fragment) {
    private val viewModel by viewModel<MvvmViewModel>()
    private val state = MvvmState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        (view as ViewGroup).setContent { View(state) }
        viewModel.items.observe(viewLifecycleOwner, Observer { state.items = it })
        viewModel.onUriOpen.observe(viewLifecycleOwner, Observer {
            startActivity(Intent(Intent.ACTION_VIEW, it))
        })
        return view
    }

    @Composable
    private fun View(state: MvvmState) {
        MaterialTheme {
            Column {
                TopAppBar(title = { Text(text = "MVVM") })
                VerticalScroller {
                    Column {
                        state.items.forEach { Item(it) }
                    }
                }
            }
            FloatingActionButton(modifier = LayoutAlign.BottomRight + LayoutPadding(16.dp)) {
                viewModel.clickedFab()
            }
        }
    }

    @Composable
    private fun Item(model: MvvmListItemModel) {
        Ripple(bounded = true) {
            Clickable(onClick = { viewModel.clickedItem(model.link) }) {
                Container(padding = EdgeInsets(16.dp)) {
                    Row(arrangement = Arrangement.Center) {
                        Text(
                            modifier = LayoutGravity.Center + LayoutPadding(right = 16.dp),
                            text = model.vote
                        )
                        Stack {
                            Column(
                                modifier = LayoutGravity.CenterLeft + LayoutPadding(right = 56.dp)
                            ) {
                                Text(
                                    text = model.title,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = model.name,
                                    style = TextStyle(fontSize = 12.sp),
                                    modifier = LayoutPadding(top = 4.dp)
                                )
                            }
                            Ripple(bounded = false) {
                                Clickable(onClick = { viewModel.clickedIcon(model.id) }) {
                                    Container(
                                        width = 40.dp,
                                        height = 40.dp,
                                        modifier = LayoutGravity.CenterRight
                                    ) {
                                        DrawVector(
                                            alignment = Alignment.Center,
                                            vectorImage = vectorResource(id = R.drawable.ic_baseline_insert_emoticon_24)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    private fun DefaultPreview() {
        View(
            MvvmState(
                listOf(
                    MvvmListItemModel("", "1", "title", "name"),
                    MvvmListItemModel("", "2", "title", "name"),
                    MvvmListItemModel("", "3", "title", "name")
                )
            )
        )
    }
}
