package io.jamshid.pdpuz.domain.usecases

import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.course.Course
import javax.inject.Inject

class AddCourse @Inject constructor(private val pdpDao: PdpDao){
    suspend operator fun invoke(course: Course){
           pdpDao.insertCourse(course)
    }
}