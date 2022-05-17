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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        if (auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }

        val et_login_email = findViewById<EditText>(R.id.et_login_email)
        val et_login_password = findViewById<EditText>(R.id.et_login_password)

        val btn_login = findViewById<Button>(R.id.btn_login)
        val tv_register = findViewById<TextView>(R.id.tv_register)

        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }

        btn_login.setOnClickListener {
            when {
                TextUtils.isEmpty(et_login_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter email.", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(et_login_password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    val email: String = et_login_email.text.toString().trim { it <= ' ' }
                    val password: String = et_login_password.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "You are logged successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra(
                                    "user_id",
                                    FirebaseAuth.getInstance().currentUser!!.uid
                                )
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                }

            }
        }

    }
}