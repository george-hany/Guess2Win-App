package com.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(var rightSpace: Int, var leftSpace: Int, var topSpace: Int, var bottomSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = bottomSpace
        outRect.top = topSpace
        outRect.left = leftSpace
        outRect.right = rightSpace
    }
}