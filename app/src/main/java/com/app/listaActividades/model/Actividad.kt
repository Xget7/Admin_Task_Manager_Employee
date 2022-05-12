package com.app.listaActividades.model

data class Actividad(
    var id: String?= null,
    val toId : String?= null,
    val activityClient : String?= null,
    val activityDireccion : String?= null,
    val activityTitle: String? = null,
    val activityDescription:String? = null,
    val activityType: String? = null,
    val activityTime: String? = null,
    val activityFinishedTime: String?= null,
    val state: String? = null,
){
    fun toMap() : HashMap<String, Any?> = hashMapOf(
        "id" to id,
        "toId" to toId,
        "activityClient" to activityClient,
        "activityDireccion" to activityDireccion,
        "activityTitle" to activityTitle,
        "activityDescription" to activityDescription,
        "activityType" to activityType,
        "activityTime" to activityTime,
        "activityFinishedTime" to activityFinishedTime,
        "state" to state
    )
}
