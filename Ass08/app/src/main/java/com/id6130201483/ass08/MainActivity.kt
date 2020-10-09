package com.id6130201483.ass08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var employeeList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_addEmployee.setOnClickListener() {
            val i = Intent(this, InsertActivity::class.java)
            startActivity(i)
        }

        recyclerView.adapter = EmployeeAdapter(this.employeeList, applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        callEmployee()
    }

    private fun callEmployee() {
        employeeList.clear()

        val serv: EmployeeAPI = Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(EmployeeAPI::class.java)

        serv.getAllEmployee().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
                response.body()?.forEach {
                    employeeList.add(Employee(it.empName, it.empGender, it.empEmail, it.empSalary))
                }

                recyclerView.adapter = EmployeeAdapter(employeeList, applicationContext)
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                return t.printStackTrace()
            }
        })
    }
}