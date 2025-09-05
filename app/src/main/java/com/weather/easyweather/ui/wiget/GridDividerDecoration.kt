package com.weather.easyweather.ui.wiget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class GridDividerDecoration(
    private val spanCount: Int,
    @ColorInt private val color: Int = 0x1FFFFFFF, // 半透明白
    private val sizeDp: Float = 0.5f,                      // 线宽（dp）
    private val includeEdge: Boolean = false,              // 是否画外边缘
    private val drawLastRow: Boolean = false,              // 是否画最后一行的底线
    private val drawLastCol: Boolean = false               // 是否画最后一列的右线
) : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }
    private var sizePx: Int = 1

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        sizePx = dpToPx(parent, sizeDp)
        paint.color = color

        val childCount = parent.childCount
        val itemCount = parent.adapter?.itemCount ?: 0
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val lp = child.layoutParams as RecyclerView.LayoutParams
            val pos = lp.viewAdapterPosition
            if (pos == RecyclerView.NO_POSITION) continue

            val col = pos % spanCount
            val isLastCol = col == spanCount - 1
            val lastRowStart = itemCount - (itemCount % spanCount).let { if (it == 0) spanCount else it }
            val isLastRow = pos >= lastRowStart

            // 右侧竖线
            if (!(isLastCol && !drawLastCol)) {
                val left = child.right + lp.rightMargin
                val right = left + sizePx
                val top = child.top - lp.topMargin
                val bottom = child.bottom + lp.bottomMargin + if (!isLastRow || drawLastRow) sizePx else 0
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
            }
            // 底部横线
            if (!(isLastRow && !drawLastRow)) {
                val left = child.left - lp.leftMargin
                val right = child.right + lp.rightMargin + if (!isLastCol || drawLastCol) sizePx else 0
                val top = child.bottom + lp.bottomMargin
                val bottom = top + sizePx
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
            }
        }

        if (includeEdge) {
            // 可按需加：最外圈上/左边缘线（通常不需要，这里留空）
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        sizePx = dpToPx(parent, sizeDp)
        val pos = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        if (pos == RecyclerView.NO_POSITION) return
        val col = pos % spanCount
        val isLastCol = col == spanCount - 1
        val itemCount = parent.adapter?.itemCount ?: 0
        val lastRowStart = itemCount - (itemCount % spanCount).let { if (it == 0) spanCount else it }
        val isLastRow = pos >= lastRowStart

        val right = if (isLastCol && !drawLastCol) 0 else sizePx
        val bottom = if (isLastRow && !drawLastRow) 0 else sizePx
        val left = if (includeEdge && col == 0) sizePx else 0
        val top = if (includeEdge && pos < spanCount) sizePx else 0
        outRect.set(left, top, right, bottom)
    }

    private fun dpToPx(rv: RecyclerView, dp: Float): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp, rv.resources.displayMetrics
        ).toInt().coerceAtLeast(1)
}