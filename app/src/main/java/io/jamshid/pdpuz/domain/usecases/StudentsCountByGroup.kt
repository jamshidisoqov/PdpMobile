package io.jamshid.pdpuz.domain.usecases

import io.jamshid.pdpuz.data.local.dao.PdpDao
import javax.inject.Inject

class StudentsCountByGroup @Inject constructor(private val pdpDao: PdpDao) {

    suspend operator fun invoke(name: String):Int{
        return pdpDao.getStudentsCountByGroup(name)
    }

}