package io.jamshid.pdpuz.data.local.entities.mentor

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Mentor(
    @PrimaryKey(autoGenerate = true) val mentorId: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val courseId: String
):Serializable