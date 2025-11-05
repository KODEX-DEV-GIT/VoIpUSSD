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


import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.graphics.Point
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

class SplashLoadingService : Service() {

    private var layout: LinearLayout? = null
    private lateinit var wm: WindowManager

    // Helper to get screen size for all API levels
    @Suppress("DEPRECATION", "unused")
    private fun getScreenSize(): Point {
        val size = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val bounds = windowMetrics.bounds
            size.x = bounds.width()
            size.y = bounds.height()
        } else {
            wm.defaultDisplay.getSize(size)
        }
        return size
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("ResourceAsColor")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val size = getScreenSize()

        @Suppress("DEPRECATION") val mLayoutFlag: Int =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                WindowManager.LayoutParams.TYPE_PHONE
            }

        val paddingInDp = 100
        val scale = resources.displayMetrics.density
        val paddingInPx = (paddingInDp * scale + 0.5f).toInt()

        layout = LinearLayout(this).apply {
            setBackgroundColor(resources.getColor(R.color.blue_background, null))
            orientation = LinearLayout.VERTICAL
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            mLayoutFlag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.RGB_565
        )

        // Add ImageView for the logo
        val imageView = ImageView(this).apply {
            setImageResource(R.drawable.favico)
            setPaddingRelative(0, paddingInPx, 0, paddingInPx)
        }
        val paramsLL = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0
        ).apply {
            gravity = Gravity.CENTER
            weight = 1f
        }

        val logoContainer = RelativeLayout(this).apply {
            addView(imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { addRule(RelativeLayout.CENTER_IN_PARENT) })
        }
        layout?.addView(logoContainer, paramsLL)

        // Add GifImageView for loading animation
        val gifImageView = GifImageView(this).apply {
            setGifImageResource(R.drawable.loading)
            setPaddingRelative(0, paddingInPx, 0, paddingInPx)
        }
        val gifContainer = RelativeLayout(this).apply {
            addView(gifImageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { addRule(RelativeLayout.CENTER_IN_PARENT) })
        }
        layout?.addView(gifContainer, paramsLL)

        wm.addView(layout, params)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Handler(Looper.getMainLooper()).postDelayed({
            layout?.let {
                wm.removeView(layout)
                layout = null
            }
        }, 500)
    }
}

//import android.annotation.SuppressLint
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.graphics.PixelFormat
//import android.graphics.Point
//import android.os.Build
//import android.os.Handler
//import android.os.IBinder
//import android.os.Looper
//import android.view.Gravity
//import android.view.ViewGroup
//import android.view.WindowManager
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.RelativeLayout
//
//class SplashLoadingService : Service() {
//
//    private var layout: LinearLayout? = null
//    private lateinit var wm: WindowManager
//    @Suppress("DEPRECATION")
//    private fun getScreenSize(): Point {
//        val size = Point()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            val windowMetrics = wm?.currentWindowMetrics
//            val bounds = windowMetrics?.bounds
//            size.x = bounds?.width() ?: 0
//            size.y = bounds?.height() ?: 0
//        } else {
//            val display = wm?.defaultDisplay
//            display?.getSize(size)
//        }
//        return size
//    }
//    override fun onBind(intent: Intent): IBinder? {
//        return null
//    }
//
//    @SuppressLint("ResourceAsColor")
//    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val size = Point()
//        wm.defaultDisplay.getSize(size)
//        val mLayoutFlag: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//        else WindowManager.LayoutParams.TYPE_PHONE
//
//        val paddingInDp = 100
//        val scale = resources.displayMetrics.density
//        val paddingInPx = (paddingInDp * scale + 0.5f).toInt()
//
//        layout = LinearLayout(this)
//        layout?.setBackgroundColor(R.color.blue_background)
//        layout?.orientation = LinearLayout.VERTICAL
//
//        val params = WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
//                mLayoutFlag, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//                PixelFormat.RGB_565)
//
//        val imageView = ImageView(this)
//        imageView.setImageResource(R.drawable.favico)
//        imageView.setPaddingRelative(0, paddingInPx, 0, paddingInPx)
//        val params_ll = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
//        params_ll.gravity = Gravity.CENTER
//        params_ll.weight = 1f
//
//        var relativeLayout = RelativeLayout(this)
//        var rp = RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT)
//        rp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
//        relativeLayout.addView(imageView, rp)
//        layout?.addView(relativeLayout, params_ll)
//
//        val gifImageView = GifImageView(this)
//        gifImageView.setGifImageResource(R.drawable.loading)
//        gifImageView.setPaddingRelative(0, paddingInPx, 0, paddingInPx)
//
//        relativeLayout = RelativeLayout(this)
//        rp = RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT)
//        rp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
//        relativeLayout.addView(gifImageView, rp)
//
//        layout?.addView(relativeLayout, params_ll)
//
//        wm?.addView(layout, params)
//        return START_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Handler(Looper.getMainLooper()).postDelayed({
//            layout?.let {
//                wm.removeView(layout)
//                layout = null
//            }
//        }, 500)
//    }
//
//}