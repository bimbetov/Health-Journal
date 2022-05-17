package bimbetov.com.example.healthjournal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import bimbetov.com.example.healthjournal.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tv_login = findViewById<TextView>(R.id.tv_login)

        tv_login.setOnClickListener {
            //startActivity(Intent(this, LoginActivity::class.java))
            onBackPressed()
        }
        val btn_register = findViewById<Button>(R.id.btn_register)
        val et_register_email = findViewById<EditText>(R.id.et_register_email)
        val et_register_password = findViewById<EditText>(R.id.et_register_password)
        //val et_register_email = findViewById<EditText>(R.id.et_register_email)
        btn_register.setOnClickListener {
            when{
                TextUtils.isEmpty(et_register_email.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this,"Please enter email.", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(et_register_password.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(this,"Please enter password.", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    val email: String = et_register_email.text.toString().trim { it <= ' '}
                    val password: String = et_register_password.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener (
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful){
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(this, "You are registered successfully", Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                                }

                            })
                }

            }
        }

    }
}