package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class TriangleStripeView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        if (attrs != null)
            initWithAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
        if (attrs != null)
            initWithAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        if (attrs != null)
            initWithAttrs(attrs)
    }

    init {
        setWillNotDraw(false)
    }


    private val path = Path()
    private val paint = Paint()

    var triangleStripeHasShadow = false
        set(value) {
            field = value
            invalidate()
        }

    var triangleStripeColor: ColorStateList? = null
        set(value) {
            field = value
            invalidate()
        }

    var triangleCount = 3
        set(value) {
            field = value
            invalidate()
        }

    var triangleStripeDistanceFromEnd = 8.0f
        set(value) {
            field = value
            invalidate()
        }

    var distanceBetweenStripes = 4.0f
        set(value) {
            field = value
            invalidate()
        }

    var triangleStripeThickness = 6.0f
        set(value) {
            field = value
            invalidate()
        }

    var shouldShow = false
        set(value) {
            field = value
            invalidate()
        }

    private fun initWithAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TriangleStripeView,
            0, 0
        ).apply {

            try {
                triangleStripeColor = getColorStateList(R.styleable.TriangleStripeView_triangleStripeColor)
                triangleStripeDistanceFromEnd =
                    getDimension(R.styleable.TriangleStripeView_triangleStripeDistanceFromEnd, 8.0f)
                triangleCount = getInt(R.styleable.TriangleStripeView_triangleCount, 3)
                distanceBetweenStripes = getDimension(R.styleable.TriangleStripeView_distanceBetweenStripes, 4.0f)
                triangleStripeThickness = getDimension(R.styleable.TriangleStripeView_triangleStripeThickness, 8.0f)
                triangleStripeHasShadow = getBoolean(R.styleable.TriangleStripeView_triangleStripeHasShadow, false)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (!shouldShow) return

        paint.apply {

            strokeWidth = triangleStripeThickness
            color = triangleStripeColor?.defaultColor ?: Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true

            if (triangleStripeHasShadow) {
                setShadowLayer(Util.convertDpToPixel(1.0f, context), 1.0f, 1.0f, Color.BLACK)
                setLayerType(LAYER_TYPE_SOFTWARE, this)
            }

        }

        drawTriangles()

        canvas.drawPath(path, paint)

    }


    private fun drawTriangles() {
        val w = width.toFloat()

        val Tw = triangleStripeDistanceFromEnd // Ribbon width
        val Th = Tw // Ribbon height
        val d = distanceBetweenStripes // Ribbon height

        path.fillType = Path.FillType.EVEN_ODD


        for (i in 1..triangleCount) {
            path.moveTo(w - Tw - i * d, 0.0f)
            path.lineTo(w - Tw - i * d, 0.0f) // Left Corner
            path.lineTo(w, Th + i * d) // Right Bottom Corner
        }

    }

}