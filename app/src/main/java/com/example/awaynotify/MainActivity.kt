package com.example.awaynotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Provider

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(FirebaseAuth.getInstance().currentUser==null)
        {
            Log.e("Tag","User Not Signed in")
            val i:Intent= Intent(this,LoginActivity::class.java)
            startActivity(i)
            Log.e("Tag","User ne activity bhej di")
            finish()

        }
        else
        {
              button.setOnClickListener{
                  AuthUI.getInstance()
                      .signOut(this)
                      .addOnCompleteListener{
                          val i:Intent= Intent(this,LoginActivity::class.java)
                          startActivity(i)
                      }
              }

        }
    }

}