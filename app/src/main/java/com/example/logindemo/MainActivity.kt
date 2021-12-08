package com.example.logindemo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var callback: CallbackManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callback= CallbackManager.Factory.create()
        loginButton.registerCallback(callback, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                textView.text =" login canceled"
            }

            override fun onError(error: FacebookException) {
            }

            override fun onSuccess(result: LoginResult) {
                intent = Intent(this@MainActivity,MainActivity2::class.java)
                startActivity(intent)
                textView.text= "Login Success${result?.accessToken?.userId}"+
                        "${result?.accessToken?.token}"
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callback?.onActivityResult(requestCode,resultCode,data)

    }

}