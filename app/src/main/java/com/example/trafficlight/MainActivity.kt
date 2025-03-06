package com.example.trafficlight

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var startButton: View
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redLight = findViewById(R.id.redLight)
        yellowLight = findViewById(R.id.yellowLight)
        greenLight = findViewById(R.id.greenLight)
        startButton = findViewById(R.id.startButton)

        makeCircle(redLight)
        makeCircle(yellowLight)
        makeCircle(greenLight)

        startButton.setOnClickListener {
            startTrafficLightSequence()
        }
    }

    private fun makeCircle(view: View) {
        view.post {
            val size = min(view.width, view.height)
            val layoutParams = view.layoutParams
            layoutParams.width = size
            layoutParams.height = size
            view.layoutParams = layoutParams

            view.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
        }
    }

    // 开始红绿灯的变化
    private fun startTrafficLightSequence() {
        startButton.isEnabled = false // 禁用按钮

        // 红灯亮 2 秒
        redLight.visibility = View.VISIBLE
        redLight.backgroundTintList = ColorStateList.valueOf(Color.RED)
        handler.postDelayed({
            redLight.visibility = View.INVISIBLE
            flashYellowLight(4) // 黄灯闪烁 4 次（慢速）
        }, 2000)
    }

    // 黄灯闪烁
    private fun flashYellowLight(times: Int) {
        if (times <= 0) {
            yellowLight.visibility = View.INVISIBLE
            greenLight.visibility = View.VISIBLE
            greenLight.backgroundTintList = ColorStateList.valueOf(Color.GREEN)

            // 绿灯亮 4 秒
            handler.postDelayed({
                greenLight.visibility = View.INVISIBLE
                startButton.isEnabled = true // 重新启用按钮
            }, 4000)
            return
        }

        yellowLight.visibility = if (yellowLight.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
        yellowLight.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)

        handler.postDelayed({
            flashYellowLight(times - 1)
        }, 1000)
    }
}
