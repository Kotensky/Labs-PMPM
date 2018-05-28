package com.kotensky.lab2intent

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import net.kotensky.lab2intent.R
import net.kotensky.lab2intent.R.id.*

class LoginActivity : AppCompatActivity() {

    companion object {
        val PHONE_KEY = "phone_key"
        val NAME_KEY = "name_key"
        val SURNAME_KEY = "surname_key"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private var isPrefsContainsValues = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        isPrefsContainsValues = sharedPreferences.contains(PHONE_KEY) &&
                sharedPreferences.contains(NAME_KEY) && sharedPreferences.contains(SURNAME_KEY)

        setContentView(R.layout.activity_login)
        if (isPrefsContainsValues) initPrefsValues()

        initConfirmButton()
    }

    private fun initPrefsValues() {
        input_phone.setText(sharedPreferences.getString(PHONE_KEY, ""))
        input_name.setText(sharedPreferences.getString(NAME_KEY, ""))
        input_surname.setText(sharedPreferences.getString(SURNAME_KEY, ""))
    }

    private fun initConfirmButton() {
        confirm_btn.text = if (isPrefsContainsValues) "Ввійти" else "Зареєстуватись"

        confirm_btn.setOnClickListener {
            if (input_name.text.isEmpty() || input_surname.text.isEmpty() || input_phone.text.isEmpty()) {
                Toast.makeText(applicationContext, "Усі поля повинні бути заповненими", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val editor = sharedPreferences.edit()
            editor.putString(PHONE_KEY, input_phone.text.toString())
            editor.putString(NAME_KEY, input_name.text.toString())
            editor.putString(SURNAME_KEY, input_surname.text.toString())
            editor.apply()

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra(PHONE_KEY, input_phone.text.toString())
            intent.putExtra(NAME_KEY, input_name.text.toString())
            intent.putExtra(SURNAME_KEY, input_surname.text.toString())
            finish()
            startActivity(intent)
        }
    }
}
