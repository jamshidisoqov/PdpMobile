package io.jamshid.pdpuz.domain.usecases

import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.group.Group
import javax.inject.Inject

class ActiveGroupsByCourse @Inject constructor(private val pdpDao: PdpDao) {
    suspend operator fun invoke(name:String):List<Group>{
        return pdpDao.getGroupsByCourseActive(name)
    }
}