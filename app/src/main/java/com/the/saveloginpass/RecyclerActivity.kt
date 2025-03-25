package com.the.saveloginpass

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

import java.util.ArrayList


class RecyclerActivity : AppCompatActivity() {

    private var recycler_view: RecyclerView? = null
    var image = arrayOf(R.drawable.gmail, R.drawable.instagram, R.drawable.upwork)

    var array_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)


        array_list.add("Gmail")
        array_list.add("Instagram")
        array_list.add("Upwork")

        recycler_view = findViewById(R.id.recycler_view)
        recycler_view!!.setLayoutManager(LinearLayoutManager(this))
        val adapter = Adapter(image, this, array_list, this@RecyclerActivity)
        recycler_view!!.setAdapter(adapter)


    }

    fun activity_change(adapter_position: Int) {
        val intent: Intent
        when (adapter_position) {
            0 -> {
               intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                intent = Intent(this@RecyclerActivity, InstagramActivity::class.java)
                startActivity(intent)
            }
            2 -> {
                intent = Intent(this@RecyclerActivity, UpworkActivity::class.java)
                startActivity(intent)
            }
        }
    }

}