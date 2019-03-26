package com.misaka.huesapp.model

class FilterHelper {
    companion object {
        fun isContains(filters: ArrayList<Filter>?, name: String): Boolean {
            for (f in filters!!) {
                if (f.name == name) return true
            }
            return false
        }
        fun findFilterId(filters: ArrayList<Filter>?, filterName: String): Filter {
            val list = filters
            list?.removeIf { f -> f.name != filterName }
            return list!![0]
        }
        fun deleteElement(filters: MutableList<Filter>, name: String) {
            filters.removeIf { f -> f.name == name }
        }
    }
}
