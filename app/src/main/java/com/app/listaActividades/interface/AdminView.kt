package com.app.listaActividades.`interface`

import com.app.listaActividades.model.Actividad
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference

interface AdminView {

    suspend fun getEmpleados() : DatabaseReference

    suspend fun getEmpleadoById(id: String) : DatabaseReference

    suspend fun getActividadById(empleadoId : String , actividadId: String) : Task<DataSnapshot>

    suspend fun getActividadesByUserId(empleadoId : String) : DatabaseReference

    suspend fun createActivityForUser(empleadoId: String, actividad: Actividad) : Task<Void>

    suspend fun updateActivity(empleadoId: String, newActividad: Actividad) : Task<Void>

    suspend fun deleteActivity(empleadoId: String,  actividadId : String) : Task<Void>

    suspend fun deleteEmpleado(empleadoId: String) : Task<Void>


}