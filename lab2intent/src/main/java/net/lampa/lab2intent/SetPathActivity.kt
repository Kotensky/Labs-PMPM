package net.lampa.lab2intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_set_path.*


class SetPathActivity : AppCompatActivity() {

    companion object {
        val POINT_FROM_KEY = "point_from_key"
        val POINT_TO_KEY = "point_to_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_path)
        confirm_btn.setOnClickListener {
            if (from_street_edt.text.isEmpty() || from_house_edt.text.isEmpty()
                    || to_street_edt.text.isEmpty() || to_house_edt.text.isEmpty()) {
                Toast.makeText(applicationContext, "Усі поля повинні бути заповненими", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent()
            intent.putExtra(POINT_FROM_KEY, " вул. ${from_street_edt.text} буд. ${from_house_edt.text}")
            intent.putExtra(POINT_TO_KEY, " вул. ${to_street_edt.text} буд. ${to_house_edt.text}")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
