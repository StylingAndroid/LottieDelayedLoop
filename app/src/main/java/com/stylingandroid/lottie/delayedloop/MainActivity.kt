package com.stylingandroid.lottie.delayedloop

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.stylingandroid.lottie.delayedloop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.restartButton.setOnClickListener {
            resetLottieAnimation()
        }

        resetLottieAnimation()
    }

    private fun resetLottieAnimation() {
        with(binding.lottieAnimation) {
            setMinAndMaxProgress(0f, 1f)
            frame = 0
            addAnimatorUpdateListener(UpdateListener(this))
            playAnimation()
        }
    }

    private class UpdateListener(private val lottieAnimationView: LottieAnimationView) :
        ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator?) {
            if (lottieAnimationView.frame > LOOP_START_FRAME) {
                lottieAnimationView.removeUpdateListener(this)
                lottieAnimationView.setMinAndMaxFrame(LOOP_START_FRAME, LOOP_END_FRAME)
            }
        }
    }

    companion object {
        private const val LOOP_START_FRAME = 39
        private const val LOOP_END_FRAME = 80
    }
}
