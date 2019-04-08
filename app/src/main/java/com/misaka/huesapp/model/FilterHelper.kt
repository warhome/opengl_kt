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
            filters?.removeIf { f -> f.name != filterName }
            return filters!![0]
        }
        fun deleteElement(filters: MutableList<Filter>, name: String) {
            filters.removeIf { f -> f.name == name }
        }
    }
}
