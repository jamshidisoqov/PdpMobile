package io.jamshid.pdpuz.data.local.entities.mentor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mentor(
    @PrimaryKey val mentorId: Int? = null,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val courseId: String
)