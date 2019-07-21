package com.mustafa.bookmarkviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes

class TriangleView : FrameLayout {

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


//    private val linearGradient: LinearGradient

    private val path = Path()
    private val paint = Paint()

    private var triangleHasShadow = false
    private var triangleColor: ColorStateList? = null
    private var triangleWidth = 8.0f
    private var triangleHeight = 8.0f


    init {

//        linearGradient =
//            LinearGradient(
//                0.0f,
//                0.0f,
//                0.0f,
//                Th,
//                Color.parseColor("#FF1111"),
//                Color.parseColor("#FF7777"),
//                Shader.TileMode.MIRROR
//            )
    }

    private fun initWithAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TriangleView,
            0, 0
        ).apply {

            try {
                triangleColor = getColorStateList(R.styleable.TriangleView_triangleColor)
                triangleWidth = getDimension(R.styleable.TriangleView_triangleWidth, 8.0f)
                triangleHeight = getDimension(R.styleable.TriangleView_triangleHeight, 8.0f)
                triangleHasShadow = getBoolean(R.styleable.TriangleView_triangleHasShadow, false)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {

        paint.strokeWidth = 2.0f
        paint.color = triangleColor?.defaultColor?:Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
//        paint.shader = linearGradient

        if(triangleHasShadow){
            paint.setShadowLayer(Util.convertDpToPixel(2.0f, context), -2.0f, -2.0f, Color.BLACK)
            setLayerType(LAYER_TYPE_SOFTWARE, paint)
        }

        drawTriangle()

        canvas?.drawPath(path, paint)

    }


    private fun drawTriangle() {
        val w = width.toFloat()

        path.fillType = Path.FillType.EVEN_ODD

        val Tw = triangleWidth // Ribbon width
        val Th = triangleHeight // Ribbon height

        path.moveTo(w - Tw, 0.0f)
        path.lineTo(w - Tw, 0.0f) // Left Corner
        path.lineTo(w, Th) // Right Bottom Corner
        path.lineTo(w, 0.0f) // Right Top Corner

        path.close()
    }

}