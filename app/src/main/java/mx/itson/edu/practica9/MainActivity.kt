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
    private val userRef = FirebaseDatabase.getInstance().getReference("Users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_save).apply { setOnClickListener { saveMarkFromForm() } }

        userRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                writeMark(snapshot.getValue(User::class.java))
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) { }

        })

    }

    private fun writeMark(it: User?) {
        findViewById<TextView>(R.id.tv_lista).apply { append("$text\n") }
        //val listV = findViewById<TextView>(R.id.tv_lista)
        //val text = listV.text.toString() + it.toString() + "\n"
        //listV.text=text
    }

    private fun saveMarkFromForm() {
        userRef.push().setValue(User(
            (findViewById<EditText>(R.id.et_nombre)).toString(),
            (findViewById<EditText>(R.id.et_apellido)).toString(),
            (findViewById<EditText>(R.id.et_edad)).toString()
        ))
    }
}