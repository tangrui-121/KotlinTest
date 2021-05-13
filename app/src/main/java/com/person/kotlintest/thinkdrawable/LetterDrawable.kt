package com.person.kotlintest.thinkdrawable

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log

/**
 * @anthor tr
 * @date 2021/5/12
 * @desc
 */
class LetterDrawable : Drawable() {
    val tag = "LetterDrawable"

    var letter: Char = 'A'

    val paint = Paint().apply {
        textSize = 60f
        color = Color.CYAN
    }

    override fun draw(canvas: Canvas) {
        Log.d(tag, "on draw")
        canvas.drawText(letter.toString(), 60f, 60f, paint)
    }

    override fun setAlpha(alpha: Int) {
        //ignore
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        //ignore
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}
