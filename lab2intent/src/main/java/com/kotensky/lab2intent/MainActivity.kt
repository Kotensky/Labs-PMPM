package com.kotensky.lab2intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kotensky.lab2intent.LoginActivity.Companion.NAME_KEY
import com.kotensky.lab2intent.LoginActivity.Companion.PHONE_KEY
import com.kotensky.lab2intent.LoginActivity.Companion.SURNAME_KEY
import com.kotensky.lab2intent.SetPathActivity.Companion.POINT_FROM_KEY
import com.kotensky.lab2intent.SetPathActivity.Companion.POINT_TO_KEY
import kotlinx.android.synthetic.main.activity_main.*
import net.kotensky.lab2intent.R

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_SET_PATH = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        full_name_txt.text = "${intent.getStringExtra(NAME_KEY)} ${intent.getStringExtra(SURNAME_KEY)}"
        phone_txt.text = intent.getStringExtra(PHONE_KEY)

        set_path_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, SetPathActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SET_PATH)
        }

        call_taxi_btn.setOnClickListener {
            Toast.makeText(applicationContext, "Очікуйте на таксі!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_SET_PATH){
            address_txt.text = "Початкова точка: ${data?.getStringExtra(POINT_FROM_KEY)}" +
                    "\nКінцева точка: ${data?.getStringExtra(POINT_TO_KEY)}"
            call_taxi_btn.isEnabled = true
        }
    }
}
