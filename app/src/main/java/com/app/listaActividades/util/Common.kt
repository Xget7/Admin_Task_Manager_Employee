package com.app.listaActividades.util

import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.regex.Pattern

class Common {

    companion object  {
        val TASK_ID = "actividadId"
        val EMPLEADO_ID = "empleadoId"
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        fun isValidString(str: String): Boolean {
            return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
        }
        fun isValidPassword(str: String): Boolean {
            return str.length >= 6
        }
        fun isValidName(str: String): Boolean {
            return str.isNotEmpty()
        }

        fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
            clear()
            addAll(newList)
        }


    }





}