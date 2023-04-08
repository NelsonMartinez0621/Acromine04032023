package com.example.acromine04032023

import android.app.AlertDialog
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.Group
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acromine04032023.adapter.AcronymAdapter
import com.example.acromine04032023.utils.UIState

@BindingAdapter("meaningList")
fun bindNewMeanings(
    view: RecyclerView,
    state: UIState?
) {
    val adapter = view.adapter as? AcronymAdapter

    when (state) {
        is UIState.LOADING -> {

        }
        is UIState.SUCCESS -> {
            view.visibility = View.VISIBLE
            adapter?.updateMeanings(
                state.response.firstOrNull()?.lfs ?: emptyList()
            )
        }
        is UIState.ERROR -> {
            AlertDialog.Builder(view.context)
                .setTitle("Error Occured")
                .setMessage(state.error.localizedMessage)
                .setNegativeButton("DISMISS") {dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
        else -> {}
    }
}

@BindingAdapter("state")
fun bindNewMeanings(
    view: ProgressBar,
    state: UIState?
) {
    if (state is UIState.LOADING) {
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter("group")
fun titleGroup(
    view: Group,
    state: UIState?
) {
    if (state is UIState.LOADING) {
        view.visibility = View.GONE
    }else{
        view.visibility = View.VISIBLE
    }
}