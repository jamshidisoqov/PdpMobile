package io.jamshid.pdpuz.domain.usecases

import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.course.Course
import io.jamshid.pdpuz.domain.models.Resourse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetCoursesUseCase
@Inject constructor(
    private val pdpDao: PdpDao
) {

    suspend operator fun invoke(): List<Course> = pdpDao.getCourses()
}