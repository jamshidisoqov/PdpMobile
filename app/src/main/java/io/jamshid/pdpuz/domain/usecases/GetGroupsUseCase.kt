package io.jamshid.pdpuz.domain.usecases

import io.jamshid.pdpuz.data.local.dao.PdpDao
import io.jamshid.pdpuz.data.local.entities.group.Group
import io.jamshid.pdpuz.domain.models.Resourse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(private val pdpDao: PdpDao){
    operator fun invoke():Flow<Resourse<List<Group>>> = flow {
        try {
            emit(Resourse.Loading())
        }catch (e:Exception){
            emit(Resourse.Error(e.localizedMessage))
        }
    }
}