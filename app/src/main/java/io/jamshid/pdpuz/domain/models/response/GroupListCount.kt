package io.jamshid.pdpuz.domain.models.response

import io.jamshid.pdpuz.data.local.entities.group.Group

data class GroupListCount(var groupList:List<Group>,var countList:List<String>)
