package io.jamshid.pdpuz.data.local.dao

import androidx.room.*
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.data.local.entities.mentor.Mentor
import io.jamshid.pdpuz.data.local.entities.student.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface PdpDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    @Transaction
    @Query("SELECT * FROM course WHERE courseName = :courseName")
    suspend fun getCourseByName(courseName: String): Course?

    @Transaction
    @Query("SELECT * FROM course")
    suspend fun getCourses(): List<Course>

    @Transaction
    @Query("SELECT EXISTS(SELECT * FROM course WHERE courseName=(:courseName))")
    suspend fun isCourseNameAlreadyExist(courseName: String): Boolean

    //Functions for modifying "mentors" table

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMentor(mentor: Mentor)

    @Transaction
    @Query("SELECT * FROM mentor WHERE courseId = :courseName")
    fun getMentorsByCourse(courseName: String): Flow<List<Mentor>>

    @Delete
    suspend fun deleteMentor(mentor: Mentor)


    //Functions for modifying "groups" table

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    @Transaction
    @Query("SELECT * FROM `group` WHERE courseId = :courseName AND isOpened=1")
    suspend fun getGroupsByCourseActive(courseName: String): List<Group>

    @Transaction
    @Query("SELECT * FROM `group` WHERE courseId = :courseName AND isOpened=0")
    suspend fun getGroupsByCourseInActive(courseName: String): List<Group>

    @Transaction
    @Query("SELECT COUNT(studentId) FROM student WHERE groupName = :groupName")
    suspend fun getStudentsCountByGroup(groupName: String): Int

    @Delete
    suspend fun deleteGroup(group: Group)


    //Functions for modifying "students" table

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM student WHERE groupName = :groupName")
    fun getStudentsByGroup(groupName: String): Flow<List<Student>>

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student WHERE groupName = :groupName")
    suspend fun deleteStudentsByFollowingGroup(groupName: String)

}