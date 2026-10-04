package com.cbvpn.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val configs = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 60, 40, 40)
        }

        val title = TextView(this).apply {
            text = "CB VPN"
            textSize = 26f
        }

        val status = TextView(this).apply {
            text = "Status: Disconnected"
            textSize = 16f
            setPadding(0, 30, 0, 30)
        }

        val input = EditText(this).apply {
            hint = "Paste vmess:// vless:// ssh:// config"
            minLines = 4
        }

        val btnAdd = Button(this).apply {
            text = "ADD CONFIG"
        }

        val btnConnect = Button(this).apply {
            text = "CONNECT"
        }

        val btnDisconnect = Button(this).apply {
            text = "DISCONNECT"
        }

        val listText = TextView(this).apply {
            textSize = 14f
            setPadding(0, 30, 0, 0)
        }

        btnAdd.setOnClickListener {
            val cfg = input.text.toString().trim()
            if (cfg.isNotEmpty()) {
                configs.add(cfg)
                listText.text = "Configs (${configs.size}):\n" +
                        configs.joinToString("\n") { "• ${it.take(50)}..." }
                input.setText("")
                Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show()
            }
        }

        btnConnect.setOnClickListener {
            if (configs.isEmpty()) {
                Toast.makeText(this, "Add config first", Toast.LENGTH_SHORT).show()
            } else {
                status.text = "Status: Connected ✅"
                Toast.makeText(this, "VPN Connecting...", Toast.LENGTH_SHORT).show()
            }
        }

        btnDisconnect.setOnClickListener {
            status.text = "Status: Disconnected"
        }

        layout.addView(title)
        layout.addView(status)
        layout.addView(input)
        layout.addView(btnAdd)
        layout.addView(btnConnect)
        layout.addView(btnDisconnect)
        layout.addView(listText)

        setContentView(layout)
    }
}
