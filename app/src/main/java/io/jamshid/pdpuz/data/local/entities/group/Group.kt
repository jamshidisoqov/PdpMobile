package io.jamshid.pdpuz.data.local.entities.group

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Group(
    @PrimaryKey(autoGenerate = false) val groupName: String,
    val courseId: String,
    val mentorId: Int,
    val startTime: String,
    val endTime: String,
    val days: String,
    val isOpened: Boolean = false
):Serializable
