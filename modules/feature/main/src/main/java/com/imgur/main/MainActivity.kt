package com.imgur.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.imgur.core_api.AppRootProvider
import com.imgur.database_api.DatabaseDao
import com.imgur.database_api.dto.StringDto
import com.imgur.main.di.MainActivityComponent
import com.imgur.network_api.NetRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var netRequest: NetRequest

    @Inject
    lateinit var database: DatabaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityComponent.create((application as AppRootProvider).getRootProvider())
            .inject(this)

        setContentView(R.layout.activity_main)

        val text = netRequest.performRequest()

        findViewById<TextView>(R.id.exampleText).text = text

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val str = findViewById<EditText>(R.id.editText).text.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                database.addDbRecord(StringDto(str))
            }
        }

        findViewById<Button>(R.id.btnLoad).setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val str = database.getAllDbRecords().joinToString("\n")

                withContext(Dispatchers.Main) {
                    findViewById<TextView>(R.id.exampleText).text = str
                }
            }
        }
    }
}
