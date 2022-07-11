package com.example.simpletodo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


var listOfTasks = mutableListOf<String>()
lateinit var adapter: TaskItemAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  onLongClickListenner = object : TaskItemAdapter.OnLongClickListenner{
            override fun onItemLongCliked(position: Int) {
                //1. remove the itemfrom the list
                listOfTasks.removeAt(position)
                //2. Notify the adapterthat our daata set has change
               adapter.notifyDataSetChanged()

                saveitems()
            }

        }

        loadItems()

        // look up recycleview in layout
         val recycleView = findViewById<RecyclerView>(R.id.recycleView)

        //  Create adapter passing in the sample user data
         adapter =TaskItemAdapter(listOfTasks, onLongClickListenner)

        // Attach the adapter to the recyclerview to populate items
        recycleView.adapter = adapter

        // Set layout manager to position the items
        recycleView.layoutManager = LinearLayoutManager(this)

        listOfTasks.add("Do laundry")
        listOfTasks.add("Go for a walk")



//     setup the button and input field
        val inputTextField=findViewById<EditText>(R.id.addtaskfield)
//        Get  a reference to the Button
//        and then set onclicklistenner

        findViewById<Button>(R.id.button).setOnClickListener {
            // 1. Grab the text the user has input into @+id/addtaskfield
            val userInputtedTask= inputTextField.text.toString()
//
//            //2 add the string to our list
            listOfTasks.add(userInputtedTask)

            //Modify the adapter that our data has been update
           adapter.notifyItemInserted(listOfTasks.size - 1)

            inputTextField.setText("")
       }
    }

    // save the data that the userhas inputted
    // save databy writting and reading from a file
    //Get the file we need
    fun getDataFile():File{
        //every line is going to represente a specific task
        return File(filesDir,"data.txt")
    }
    //Load the items by readind every linein de data file
    fun loadItems(){
    try {
        listOfTasks= org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
    }catch (ioException: IOException){
        ioException.printStackTrace()
    }
    }
    //save items by writting into our data
    fun saveitems(){
    try{
         org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
    }catch (ioException: IOException){
        ioException.printStackTrace()

       }
    }
}