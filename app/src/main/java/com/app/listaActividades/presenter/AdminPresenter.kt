package com.app.listaActividades.presenter

import com.app.listaActividades.`interface`.AdminView
import com.app.listaActividades.model.Actividad
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AdminPresenter : AdminView {

    private var db = Firebase.database
    private var mAuth = FirebaseAuth.getInstance()
    private val currentUser = mAuth.currentUser!!.uid

    override suspend fun getEmpleados(): DatabaseReference {
       return db.getReference("empleados")
    }

    override suspend fun getEmpleadoById(id: String): DatabaseReference {
        return db.getReference("empleados").child(id)
    }

    override suspend fun getActividadById(empleadoId: String, actividadId: String): Task<DataSnapshot> {
        return db.getReference("empleados").child(empleadoId).child("actividades").child(actividadId).get()
    }

    override suspend fun getActividadesByUserId(empleadoId : String): DatabaseReference {
        return db.getReference("empleados").child(empleadoId).child("actividades")
    }

    override suspend fun createActivityForUser(
        empleadoId: String,
        actividad: Actividad
    ): Task<Void> {
        return db.getReference("empleados").child(empleadoId).child("actividades").child(actividad.id!!).setValue(actividad.toMap())
    }

    override suspend fun updateActivity(empleadoId: String, newActividad: Actividad): Task<Void> {
        return db.getReference("empleados").child(empleadoId).child("actividades").child(newActividad.id!!).updateChildren(newActividad.toMap())

    }

    override suspend fun deleteActivity(empleadoId: String, actividadId : String): Task<Void> {
        return db.getReference("empleados").child(empleadoId).child("actividades").child(actividadId).removeValue()
    }

    override suspend fun deleteEmpleado(empleadoId: String): Task<Void> {
        return db.getReference("empleados").child(empleadoId).removeValue()
    }
}