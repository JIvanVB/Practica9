package mx.itson.edu.practica9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val userRef = FirebaseDatabase.getInstance().getReference("Users").apply {
        addChildEventListener(object : ChildEventListener {
                            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                                val value = snapshot.value
                                if (value !is String) return
                                if (value !is User) return
                                writeMark(value)

                                }
                            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                            override fun onChildRemoved(snapshot: DataSnapshot) {}
                            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                            override fun onCancelled(error: DatabaseError) { }
                            })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_save).apply { setOnClickListener { saveMarkFromForm() } }

    }

    private fun writeMark(mark: User) {
        val listV:TextView = findViewById<TextView>(R.id.tv_lista) as TextView
        val text = listV.text.toString() + mark.toString() + "\n"
        listV.text=text
    }

    private fun saveMarkFromForm() {
        userRef.push().setValue(User(
            (findViewById<EditText>(R.id.et_nombre)).text.toString(),
            (findViewById<EditText>(R.id.et_apellido)).text.toString(),
            (findViewById<EditText>(R.id.et_edad)).text.toString()
        ))
    }
}