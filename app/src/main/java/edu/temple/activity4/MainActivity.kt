package edu.temple.activity4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var textSizeSelector: RecyclerView
    lateinit var textSizeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textSizeSelector = findViewById(R.id.textSizeSelectorRecyclerView)
        textSizeDisplay = findViewById(R.id.textSizeDisplayTextView)
        //val textSizeViewHolder = findViewById(R.id.textSizeDisplayTextView)

        // Trying to create array of integers that are multiples of 5


        val textSizes = Array(20){(it + 1) * 5}

        // Verify correctness by examining array values.
        for(i in 0 until textSizes.size)
            Log.d("Array values", textSizes[i].toString())// Log debug, add tag "array values", value to string

        textSizeSelector.adapter = TextSizeAdapter(textSizes){
            textSizeDisplay.textSize = it
        }
        textSizeSelector.layoutManager = LinearLayoutManager(this)
    }
}


/* Convert to RecyclerView.Adapter */
class TextSizeAdapter (_textSizes: Array<Int>, _callback: (Float) -> Unit): RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    val textSizes = _textSizes
    val callback = _callback

    inner class TextSizeViewHolder(view: TextView): RecyclerView.ViewHolder(view){
        val textView = view

        init {// portion of constructor (init log)
            textView.setOnClickListener{callback(textSizes[adapterPosition].toFloat())}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5,20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.text = textSizes[position].toString()// convert to a string
        holder.textView.textSize = textSizes[position].toFloat()// concert to a float
    }

    override fun getItemCount(): Int {
        return textSizes.size
    }

}