package com.utd.indoorairmonitor.presentation

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.utd.indoorairmonitor.R
import kotlinx.android.synthetic.main.activity_setup.*

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)


        setup_button.setOnClickListener{
            // Backend saved data
            val pref = getSharedPreferences("mypref", MODE_PRIVATE);

            // If zip hasn't been inputted yet
            if (pref.getString("zipCode", "empty") == "empty") {
                // update saved data
                val editor = pref.edit()

                // zip code value
                val input1 = findViewById<View>(R.id.zip_edit) as EditText
                val zipStr = input1.text.toString()

                // monitor id value
                val input2 = findViewById<View>(R.id.monitorId_edit) as EditText
                val MIDStr = input2.text.toString()

                //  TESTING TODO: Remove
                Log.d("test zip:", zipStr);
                Log.d("test MID:", MIDStr);

                editor.putString("zipCode", zipStr);
                editor.putString("MIDCode", MIDStr);
                editor.commit(); // apply changes

                // setup activity
                startActivity(Intent(this, MainActivity::class.java));
            }
            else { // not first time setup
                /*val alertDialog: AlertDialog? = let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User clicked OK button
                            })
                        setNegativeButton(R.string.cancel,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User cancelled the dialog
                            })
                    }

                    // Create the AlertDialog
                    builder.create()
                }*/
                Log.d("test error:", "Not ready to move");
            }
        }
    }
}