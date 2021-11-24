package com.example.appgrupo15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.appgrupo15.room_database.ToDoDatabase
import androidx.room.*
import com.example.appgrupo15.room_database.ToDo
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spiTask: Spinner = view.findViewById(R.id.spiTask)
        val edtTime: EditText = view.findViewById(R.id.edtTime)
        val edtPlace: EditText = view.findViewById(R.id.edtPlace)
        val btnNewTask: Button = view.findViewById(R.id.btnNewTask)
//        var tareas = arrayOf("Estudiar", "Mercar", "Hacer ejercicio")
        var tareas: ArrayList<Task> = ArrayList()
        tareas.add(Task("Mercar", "12:00", "Exito"))
        tareas.add(Task("Hacer ejercicio", "06:00", "Gimnasio"))
        val taskAdapter = ArrayAdapter(context?.applicationContext!!, android.R.layout.simple_spinner_item, tareas)
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiTask.adapter = taskAdapter
        btnNewTask.setOnClickListener {
            var task = spiTask.selectedItem as Task
            val room: ToDoDatabase = Room
                .databaseBuilder(context?.applicationContext!!, ToDoDatabase::class.java, "ToDoDatabase").build()
            val todoTask: ToDo = ToDo(0, task.task, edtTime.text.toString(), edtPlace.text.toString())
            var todoDao = room.todoDao()
            runBlocking {
                launch {
                    var result = todoDao.insertTask(todoTask)
                }
            }
            Navigation.findNavController(view).navigate(R.id.nav_todo)
//            Toast.makeText(context?.applicationContext!!, task.time, Toast.LENGTH_LONG).show()
//            Toast.makeText(context?.applicationContext!!, spiTask.selectedItem.toString(), Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}