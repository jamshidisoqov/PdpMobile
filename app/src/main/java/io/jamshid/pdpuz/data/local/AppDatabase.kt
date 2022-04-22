package io.jamshid.pdpuz.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.data.local.entities.student.Student

@Database(
    entities = [Course::class, Group::class, Mentor::class, Student::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): PdpDao
}