package com.id6130201483.ass08

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Employee(
    @Expose
    @SerializedName("emp_name") val empName: String,

    @Expose
    @SerializedName("emp_gender") val empGender: String,

    @Expose
    @SerializedName("emp_email") val empEmail: String,

    @Expose
    @SerializedName("emp_salary") val empSalary: Int
) {
}