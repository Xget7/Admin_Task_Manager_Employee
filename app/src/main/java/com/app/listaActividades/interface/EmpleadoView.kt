package com.app.listaActividades.`interface`

import com.app.listaActividades.model.Actividad
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

interface EmpleadoView {

    suspend fun getEmpleado() : DatabaseReference

    suspend fun getRealTimeActivities() : DatabaseReference

    suspend fun updateActivity( newActividad: Actividad) : Task<Void>


    suspend fun getSpecifictActivity(id: String) : DatabaseReference


}