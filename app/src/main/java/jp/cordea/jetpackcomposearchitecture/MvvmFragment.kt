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
import jp.cordea.jetpackcomposearchitecture.response.Id
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
        viewModel.items.observe(viewLifecycleOwner, Observer { state.items = it.toState() })
        viewModel.onUriOpen.observe(viewLifecycleOwner, Observer {
            startActivity(Intent(Intent.ACTION_VIEW, it))
        })
        viewModel.onCheckedStateUpdate.observe(viewLifecycleOwner, Observer { id ->
            val item = state.items.first { it.id == id }
            item.isChecked = !item.isChecked
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
    private fun Item(model: MvvmListItemState) {
        Ripple(bounded = true) {
            Clickable(onClick = { viewModel.clickedItem(model.link) }) {
                Container(padding = EdgeInsets(16.dp)) {
                    Row(arrangement = Arrangement.Center) {
                        Text(
                            modifier = LayoutGravity.Center + LayoutPadding(right = 16.dp),
                            text = model.vote
                        )
                        Stack(modifier = LayoutWidth.Fill) {
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
                            ClickableIcon(model)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun StackScope.ClickableIcon(state: MvvmListItemState) {
        Ripple(bounded = false) {
            Clickable(onClick = { viewModel.clickedIcon(state.id) }) {
                if (state.isChecked) {
                    IconContainer {
                        DrawVector(
                            alignment = Alignment.Center,
                            vectorImage = vectorResource(
                                id = R.drawable.ic_baseline_check_24
                            )
                        )
                    }
                } else {
                    IconContainer {
                        DrawVector(
                            alignment = Alignment.Center,
                            vectorImage = vectorResource(
                                id = R.drawable.ic_baseline_add_24
                            )
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun StackScope.IconContainer(
        @Suppress("RemoveEmptyParenthesesFromAnnotationEntry") children: @Composable() () -> Unit
    ) {
        Container(
            width = 40.dp,
            height = 40.dp,
            modifier = LayoutGravity.CenterRight,
            children = children
        )
    }

    @Preview
    @Composable
    private fun DefaultPreview() {
        View(
            MvvmState(
                listOf(
                    MvvmListItemState(Id(0L), "", "1", "title", "name"),
                    MvvmListItemState(Id(0L), "", "2", "title", "name"),
                    MvvmListItemState(Id(0L), "", "3", "title", "name")
                )
            )
        )
    }
}
