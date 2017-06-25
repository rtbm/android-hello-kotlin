package com.example.m.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }
}

class MainActivityUI: AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            padding = dip(30)

            imageView(R.mipmap.ic_launcher)

            val name = editText {
                hintResource = R.string.email
                textSize = 24f
            }

            val password = editText(InputConstraints.PASSWORD) {
                hintResource = R.string.password
                textSize = 24f
            }

            button(R.string.login) {
                textSize = 26f

                onClick {
                    toast("Hello ${name.text}")
                }
            }
        }
    }
}
