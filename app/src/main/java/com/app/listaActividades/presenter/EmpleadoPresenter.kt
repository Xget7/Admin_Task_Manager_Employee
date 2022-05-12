package com.app.listaActividades.presenter

import com.app.listaActividades.`interface`.EmpleadoView
import com.app.listaActividades.model.Actividad
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EmpleadoPresenter : EmpleadoView {

    private var db = Firebase.database
    private var mAuth = FirebaseAuth.getInstance()
    private val currentUser = mAuth.currentUser!!.uid

    val usersRef = db.getReference("empleados")

    override suspend fun getEmpleado(): DatabaseReference {
        return usersRef.child(currentUser)
    }

    override suspend fun updateActivity(newActividad: Actividad): Task<Void> {
        return db.getReference("empleados")
            .child(currentUser)
            .child("actividades")
            .child(newActividad.id!!)
            .updateChildren(newActividad.toMap())

    }

    override suspend fun getRealTimeActivities(): DatabaseReference {
        return usersRef.child(currentUser).child("actividades")
    }

    override suspend fun getSpecifictActivity(id: String): DatabaseReference {
        return usersRef.child(currentUser).child("actividades").child(id)
    }


}