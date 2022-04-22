package io.jamshid.pdpuz.data.local.entities.course

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Course(
    @PrimaryKey(autoGenerate = false) val courseName: String,
    val courseDesc: String
):Serializable
