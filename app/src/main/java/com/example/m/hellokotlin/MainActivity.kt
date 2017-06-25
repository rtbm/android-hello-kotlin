package com.example.m.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    fun login(email: Editable, password: Editable) {
        val jsonPayload = "{\"email\": \"${email}\", \"password\":\"${password}\"}"

        ctx.toast("Attempt to login with '${jsonPayload}'")

        "http://www.ticktockwatch.net/auth/login".httpPost().body(jsonPayload)
            .responseString { request, response, result ->
                val (data, error) = result

                when (result) {
                    is Result.Failure -> {
                        ctx.toast("Auth failed: ${error}")
                    }
                    is Result.Success -> {
                        ctx.toast("Auth OK!")
                    }
                }
            }
    }
}

class MainActivityUI: AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            padding = dip(30)

            imageView(R.mipmap.ic_launcher)

            val email = editText {
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
                    ui.owner.login(email.text, password.text)
                }
            }
        }
    }
}
