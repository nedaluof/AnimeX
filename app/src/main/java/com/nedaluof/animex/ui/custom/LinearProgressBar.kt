package com.nedaluof.animex.ui.custom

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.nedaluof.animex.R

/**
 * Created By NedaluOf - 7/9/2023.
 */
class LinearProgressBar : View, ValueAnimator.AnimatorUpdateListener {

  private var titleSize: Float = 10f
  private var backgroundColor: Int = -1
  private var colorTitle: Int = -1
  private var toBackgroundColor: Int = -1
  private var allRadius: Int = -1
  private var layoutWidth: Int = -1
  private var layoutHeight: Int = -1
  private var toProgressInt: Int = -1
  private var toProgressPercent: Float = -1f
  private var boxShadowSize: Int = -1
  private var boxShadowSizeX: Int = 0
  private var boxShadowSizeY: Int = 0
  private var boxShadowColor: Int = -1
  private var titleProgress: String? = ""
  private var typeface: Typeface? = null
  private var backgroundPaint = Paint()
  private var toPaint = Paint()
  private var textPaint = Paint()
  private var statusToPercent: Boolean = false
  private var animateToProgress = 0f
  private var valueAnimator: ValueAnimator? = null

  var stringPercent = "100.0"
    set(value) {
      this.toProgressPercent = value.toFloat()
      this.statusToPercent = true
      onEvaluate(toProgressPercent)
      refresh()
      field = value
    }

  var progressTitle = ""
    set(value) {
      this.titleProgress = value
      field = value
      this.refresh()
    }

  constructor(context: Context?) : super(context)

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    initial(context, attrs)
  }

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ) {
    initial(context, attrs)
  }

  private fun initial(context: Context?, attrs: AttributeSet?) {
    val ta = context!!.theme.obtainStyledAttributes(attrs, R.styleable.LinearProgressBar, 0, 0)
    try {
      this.allRadius = ta.getInteger(R.styleable.LinearProgressBar_RadiusAll, 0)
      this.titleSize = ta.getDimension(
        R.styleable.LinearProgressBar_TitleSize,
        (18 * resources.displayMetrics.density)
      )
      this.backgroundColor = ta.getColor(
        R.styleable.LinearProgressBar_ProgressBackground,
        ContextCompat.getColor(context, R.color.bright_light_green)
      )
      this.toBackgroundColor = ta.getColor(
        R.styleable.LinearProgressBar_ProgressToBackground,
        ContextCompat.getColor(context, R.color.electric_violet)
      )
      this.colorTitle = ta.getColor(
        R.styleable.LinearProgressBar_ColorTitle,
        ContextCompat.getColor(context, R.color.white)
      )
      this.toProgressInt = ta.getInteger(R.styleable.LinearProgressBar_ToProgressInt, -1)
    } finally {
      ta.recycle()
    }
    this.statusToPercent = this.toProgressPercent > -1f
    if (this.statusToPercent) onEvaluate(this.toProgressPercent) else onEvaluate(this.toProgressInt.toFloat())
  }

  @SuppressLint("DrawAllocation")
  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    this.layoutWidth = this.measuredWidth
    this.layoutHeight = this.measuredHeight
    this.backgroundPaint.style = Paint.Style.FILL
    this.backgroundPaint.color = this.backgroundColor
    if (this.boxShadowSize > -1) {
      this.backgroundPaint.setShadowLayer(
        (this.boxShadowSize * resources.displayMetrics.density),
        this.boxShadowSizeX.toFloat(),
        this.boxShadowSizeY.toFloat(),
        this.boxShadowColor
      )
    }
    val rectF = RectF(0f, 0f, this.layoutWidth.toFloat(), this.layoutHeight.toFloat())
    val corners: FloatArray
    if (this.allRadius > 0) {
      corners = floatArrayOf(
        this.allRadius.toFloat(),
        this.allRadius.toFloat(),
        this.allRadius.toFloat(),
        this.allRadius.toFloat(),
        this.allRadius.toFloat(),
        this.allRadius.toFloat(),
        this.allRadius.toFloat(),
        this.allRadius.toFloat()
      )
    } else {
      corners = floatArrayOf(
        5.toFloat(),
        5.toFloat(),
        5.toFloat(),
        5.toFloat(),
        5.toFloat(),
        5.toFloat(),
        5.toFloat(),
        5.toFloat()
      )
    }
    val pathBackground = Path()
    pathBackground.addRoundRect(rectF, corners, Path.Direction.CW)
    canvas!!.drawPath(pathBackground, this.backgroundPaint)
    this.toPaint.style = Paint.Style.FILL
    this.toPaint.color = this.toBackgroundColor
    val rectFTo = RectF(0f, 0f, animateToProgress, this.layoutHeight.toFloat())
    val toPath = Path()
    toPath.addRoundRect(rectFTo, corners, Path.Direction.CW)
    canvas.drawPath(toPath, this.toPaint)
    textPaint.textSize = this.titleSize
    textPaint.typeface = Typeface.MONOSPACE
    textPaint.color = this.colorTitle
    if (typeface != null) {
      textPaint.typeface = typeface
    }
    textPaint.textAlign = Paint.Align.CENTER
    val positionX = (width / 2).toFloat()
    val positionY = ((this.layoutHeight / 2 + 10)).toFloat()
    val tempTextShow = this.titleProgress.toString()
    canvas.drawText(tempTextShow, positionX, positionY, textPaint)
  }

  private fun createAnimation(toProgress: Float) {
    val newToProgress: Float = if (this.statusToPercent) {
      (this.layoutWidth * toProgress) / 100
    } else {
      if (toProgress > this.layoutWidth) this.layoutWidth.toFloat() else toProgress
    }
    valueAnimator = ValueAnimator.ofInt(animateToProgress.toInt(), newToProgress.toInt())
    valueAnimator?.addUpdateListener(this)
    valueAnimator?.interpolator = LinearInterpolator()
    valueAnimator?.duration = 2000
    valueAnimator?.start()
  }

  override fun onAnimationUpdate(animation: ValueAnimator) {
    val increaseValue = animation.animatedValue.toString().toFloat()
    animateToProgress =
      if (this.layoutWidth < increaseValue) this.layoutWidth.toFloat() else increaseValue
    this.refresh()
  }

  private fun onEvaluate(value: Float) {
    if (this.layoutWidth == -1) {
      this.viewTreeObserver.addOnGlobalLayoutListener(object :
        ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
          viewTreeObserver.removeOnGlobalLayoutListener(this)
          layoutWidth = width
          createAnimation(value)
        }
      })
    } else {
      this.createAnimation(value)
    }
  }

  private fun refresh() {
    invalidate()
    requestLayout()
  }
}