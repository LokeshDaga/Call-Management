package com.example.awaynotify

import android.app.Activity
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {



    lateinit var providers:List<AuthUI.IdpConfig>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.e("Tag","User in Login")
        if(FirebaseAuth.getInstance().currentUser!=null) {
            Log.e("Tag","User  Signed in")
            button2.setOnClickListener{
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        startActivity(Intent(this, MainActivity::class.java))
                        this.finish()
                    }
            }


        }
        else{
            handleloginRegister()
        }
    }

    public fun handleloginRegister()
    {
        Log.e("Tag","User trying to login")
        providers= arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        val intent=AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
            .setTosAndPrivacyPolicyUrls("http://example.com","http://exapmle.com").setIsSmartLockEnabled(false).setLogo(R.drawable.ic_launcher_foreground).build()
    startActivityForResult(intent,123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123) {
            val response = IdpResponse.fromResultIntent(data)


            if (resultCode == Activity.RESULT_OK) {
             val user=FirebaseAuth.getInstance().currentUser
               Log.e("TAG","onAvtivityResult ${user.displayName}")
                Log.e("TAG","onAvtivityResult ${user.uid}")
                val i:Intent= Intent(this,MainActivity::class.java)
                startActivity(i)
                finish()
            } else {
                Log.e("Tag","User Not Signed in Error Encountered\n ${response}")

            }
        }
    }
}