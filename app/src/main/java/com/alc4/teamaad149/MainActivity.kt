package com.alc4.teamaad149

import android.animation.AnimatorInflater
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var currentScene: Scene
    private lateinit var transition: Transition

    lateinit var loveAnimation: AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_main, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_main_plg, this)


        transition = TransitionInflater.from(this).inflateTransition(R.transition.example_2)

        scene1.enter()
        currentScene = scene1

        Log.d("onCreate", "Trigger")
    }

    fun slideLeft(view: View) {
        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.translate)
        fadeAnimator?.apply {
            setTarget(btnSlide)
            start()
            Log.d("slideLeft", "slideLeft Animation")

            Toast.makeText(applicationContext, "slideLeft Animation", Toast.LENGTH_SHORT).show()
        }
        //sceneTransition()
    }

    fun openPlg(view: View) {
        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.scale)
        fadeAnimator?.apply {
            setTarget(button3)
            start()
            Log.d("openPlg", "Scale Animation")
            Toast.makeText(applicationContext, "Scale Animation", Toast.LENGTH_SHORT).show()
        }
        sceneTransition()
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "Trigger")
        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        fadeAnimator?.apply {
            setTarget(teamName)
            start()
            //teamName.text = "Team aad 149"
            Toast.makeText(applicationContext, "Fade Animation", Toast.LENGTH_SHORT).show()

            loveImageView.setBackgroundResource(R.drawable.love_heart_animation)
            loveAnimation = loveImageView.background as AnimationDrawable
            loveAnimation.start()
            Toast.makeText(applicationContext, "Frame Animation", Toast.LENGTH_SHORT).show()
        }

        fun frameAnimation(view: View) {

        }
    }

    fun returnAction(view: View) {
        Log.d("returnAction", "Trigger")
        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.translate)
        fadeAnimator?.apply {
            setTarget(button3)
            start()
            sceneTransition()
        }
    }

    fun sceneTransition() {
        Log.d("sceneTransistion", "Trigger")
        val fadeAnimator = AnimatorInflater.loadAnimator(this, R.animator.translate)
        fadeAnimator?.apply {
            setTarget(button3)
            start()
        }

        Log.d("frameAnimation", "Trigger")
        loveImageView.setBackgroundResource(R.drawable.love_heart_animation)
        loveAnimation = loveImageView.background as AnimationDrawable
        loveAnimation.start()
        currentScene = if (currentScene === scene1) {
            TransitionManager.go(scene2, transition)
            scene2
        } else {
            loveImageView.setBackgroundResource(R.drawable.love_heart_animation)
            loveAnimation = loveImageView.background as AnimationDrawable
            loveAnimation.start()
            TransitionManager.go(scene1, transition)
            scene1

        }
        Toast.makeText(applicationContext, "Scene transition", Toast.LENGTH_SHORT).show()
    }
}