package jp.cordea.jetpackcomposearchitecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.ui.core.setContent

class MvvmFragment : Fragment(R.layout.empty_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        (view as ViewGroup).setContent { }
        return view
    }
}