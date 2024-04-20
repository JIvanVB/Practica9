package mx.itson.edu.practica9

data class User(var firstname:String?=null,
                var lastName:String?=null,
                var age:String?=null){
    override fun toString() = firstname+"\t"+lastName+"\t"+age
}
