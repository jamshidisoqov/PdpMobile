package io.jamshid.pdpuz.domain.interfases

interface OnCustomItemClickListener<T> {

    fun onItemClick(name: T){

    }

    fun onEditItemClick(name: T)

    fun onTrashItemClick(name: T)

    fun showItemClick(name: T)
}