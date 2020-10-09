package com.id6130201483.ass08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        btn_add.setOnClickListener() {
            addEmployee()
        }

        btn_cancel.setOnClickListener() {
            cancel()
        }
    }

    private fun addEmployee() {
        val serv: EmployeeAPI = Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(EmployeeAPI::class.java)

        serv.insertEmployee(
            edit_name.text.toString(),
            "Male",
            edit_email.text.toString(),
            edit_salary.text.toString().toInt()
        ).enqueue(object : Callback<Employee> {
            override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Inserted success", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Inserting unsuccess", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<Employee>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Error! onFailure: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun cancel() {
        finish()
    }

    private fun radioButtonChecked(): RadioButton {
        return findViewById(radio_gender.checkedRadioButtonId)
    }
}