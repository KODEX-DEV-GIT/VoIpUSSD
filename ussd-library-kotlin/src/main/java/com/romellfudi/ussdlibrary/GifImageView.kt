/*
 * Copyright (c) 2020. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * porfile.romellfudi.com
 */

/**
 * BoostTag E.I.R.L. All Copyright Reserved
 * www.boosttag.com
 */
package com.romellfudi.ussdlibrary

import android.content.Context
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.IOException

class GifImageView : View {

    private var gifDrawable: AnimatedImageDrawable? = null
    private var glideGifDrawable: GifDrawable? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            gifDrawable?.let {
                it.setBounds(0, 0, width, height)
                it.draw(canvas)
            }
        } else {
            glideGifDrawable?.let {
                it.setBounds(0, 0, width, height)
                it.draw(canvas)
            }
        }
    }

    fun setGifImageResource(resourceId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                val imageSource =
                    ImageDecoder.createSource(context.resources, resourceId)
                gifDrawable = ImageDecoder.decodeDrawable(imageSource) as AnimatedImageDrawable
                gifDrawable?.start() // Starts the animation
                invalidate()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            // Fallback for older Android versions using Glide
            Glide.with(context)
                .asGif()
                .load(resourceId)
                .into(object : CustomTarget<GifDrawable>() {
                    override fun onResourceReady(
                        resource: GifDrawable,
                        transition: Transition<in GifDrawable>?,
                    ) {
                        glideGifDrawable = resource
                        glideGifDrawable?.start() // Start the animation
                        invalidate()
                    }

                    override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {}
                })
        }
    }

    fun setGifImageUri(uri: Uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                val imageSource = ImageDecoder.createSource(context.contentResolver, uri)
                gifDrawable = ImageDecoder.decodeDrawable(imageSource) as AnimatedImageDrawable
                gifDrawable?.start() // Starts the animation
                invalidate()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            // Fallback for older Android versions using Glide
            Glide.with(context)
                .asGif()
                .load(uri)
                .into(object : CustomTarget<GifDrawable>() {
                    override fun onResourceReady(
                        resource: GifDrawable,
                        transition: Transition<in GifDrawable>?,
                    ) {
                        glideGifDrawable = resource
                        glideGifDrawable?.start() // Start the animation
                        invalidate()
                    }

                    override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {}
                })
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            gifDrawable?.start()
        } else {
            glideGifDrawable?.start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            gifDrawable?.stop()
        } else {
            glideGifDrawable?.stop()
        }
    }
}
//class GifImageView : View {
//
//    private var gifDrawable: AnimatedImageDrawable? = null
//
//    constructor(context: Context) : super(context)
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
//
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    )
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        gifDrawable?.let {
//            it.setBounds(0, 0, width, height)
//            it.draw(canvas)
//        }
//    }
//
//    fun setGifImageResource(resourceId: Int) {
//        try {
//            val inputStream = context.resources.openRawResource(resourceId)
//            val byteArray = inputStream.readBytes()
//            val imageSource: ImageDecoder.Source? =
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                    ImageDecoder.createSource(byteArray)
//                } else {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                        ImageDecoder.createSource(context.resources, resourceId)
//                    } else {
//                        null
//                    }
//                }//.inputStream()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                if (imageSource != null) {
//                    gifDrawable = ImageDecoder.decodeDrawable(imageSource) as AnimatedImageDrawable
//                    gifDrawable?.start()
//                }
//            }
//            // Starts the animation
//            invalidate()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//
//    fun setGifImageUri(uri: Uri) {
//        try {
//            val imageSource: ImageDecoder.Source? =
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    ImageDecoder.createSource(context.contentResolver, uri)
//                } else {
//                    null
//                }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                if (imageSource != null) {
//                    gifDrawable = ImageDecoder.decodeDrawable(imageSource) as AnimatedImageDrawable
//                    gifDrawable?.start() // Starts the animation
//                }
//            }
//
//            invalidate()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            gifDrawable?.start()
//        } else {
//        }
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            gifDrawable?.stop()
//        }
//    }
//}
///**
// * Designed view Android splashing dialog
// *
// * @author Romell Dominguez
// * @version 1.1.i 2019/04/18
// * @since 1.1.i
// */
//class GifImageView : View {
//
//    private var mInputStream: InputStream? = null
//    private var mMovie: Movie? = null
//    private var mWidth: Int = 0
//    private var mHeight: Int = 0
//    private var mStart: Long = 0
//    private var mContext: Context? = null
//
//    constructor(context: Context) : super(context) {
//        this.mContext = context
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
//        this.mContext = context
//        if (attrs.getAttributeName(1) == "background") {
//            val id = Integer.parseInt(attrs.getAttributeValue(1).substring(1))
//            setGifImageResource(id)
//        }
//    }
//
//    private fun init() {
//        isFocusable = true
//        mMovie = Movie.decodeStream(mInputStream)
//        mWidth = mMovie!!.width()
//        mHeight = mMovie!!.height()
//        requestLayout()
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        setMeasuredDimension(mWidth, mHeight)
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        val now = SystemClock.uptimeMillis()
//        if (mStart == 0L) {
//            mStart = now
//        }
//        mMovie?.let {
//            var duration = mMovie!!.duration()
//            if (duration == 0) {
//                duration = 1000
//            }
//            val relTime = ((now - mStart) % duration).toInt()
//            mMovie!!.setTime(relTime)
//            mMovie!!.draw(canvas, 0f, 0f)
//            invalidate()
//        }
//    }
//
//    fun setGifImageResource(id: Int) {
//        mInputStream = mContext!!.resources.openRawResource(id)
//        init()
//    }
//
//    fun setGifImageUri(uri: Uri) {
//        try {
//            mInputStream = mContext!!.contentResolver.openInputStream(uri)
//            init()
//        } catch (e: FileNotFoundException) {
//            Timber.e( "File not found")
//        }
//
//    }
//}