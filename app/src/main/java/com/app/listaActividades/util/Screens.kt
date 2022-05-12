package com.app.listaActividades.util

import com.app.listaActividades.util.Common.Companion.EMPLEADO_ID
import com.app.listaActividades.util.Common.Companion.TASK_ID

sealed class Screens(val route : String){
    object LoginScreen : Screens("login_screen")
    object RegisterScreen : Screens("register_screen")
    object EmpleadoScreen : Screens("empleado_screen")
    object EmpleadoActividadesDetailScreen : Screens("empleado_actividades_detail_screen/{$TASK_ID}"){
        fun passTaskId(actividadId : String) : String {
            return "empleado_actividades_detail_screen/$actividadId"
        }
    }
    object AdminScreen : Screens("admin_screen")
    object AdminEmpleadoDetail : Screens("admin_empleado_screen/{$EMPLEADO_ID}"){
        fun passEmpleadoId(empleadoId : String) : String {
            return "admin_empleado_screen/$empleadoId"
        }
    }
    object AdminCreateActivity : Screens("admin_create_activity_screen")
    object AdminEditActivity : Screens("admin_edit_activity_screen/{$EMPLEADO_ID}/{$TASK_ID}") {
        fun passIds(empleadoId: String, actividadId: String): String {
            return "admin_edit_activity_screen/$empleadoId/$actividadId"
        }
    }


}