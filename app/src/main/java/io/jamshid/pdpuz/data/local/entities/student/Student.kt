package io.jamshid.pdpuz.data.local.entities.student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val birthDate: String,
    val groupName: String,
    @PrimaryKey val studentId: Int? = null
)
