package com.example.rickandmorty.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecorator(private val dividerHeight: Int, private val margin: Int) : RecyclerView.ItemDecoration() {

    private val dividerPaint = Paint()

    init {
        dividerPaint.color = Color.GRAY
        dividerPaint.style = Paint.Style.FILL
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val left = parent.paddingLeft + margin
        val right = parent.width - parent.paddingRight - margin

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin + margin
            val bottom = top + dividerHeight

            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = margin
        outRect.bottom = dividerHeight + margin
    }
}