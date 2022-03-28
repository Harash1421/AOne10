package com.example.aone10

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAppearance()
        setContentView(R.layout.activity_main)

        main_buChange.setOnClickListener {
            showAppearanceChoose()
        }
    }
    //Show Appearances Choose
    @SuppressLint("CommitPrefEdits")
    private fun showAppearanceChoose(){
        var editor = getSharedPreferences("Appearance", Activity.MODE_PRIVATE).edit()
        var list = arrayOf(resources.getString(R.string.DarkMode),
        resources.getString(R.string.LightMode))
        var alert = AlertDialog.Builder(this)
        alert.setTitle(resources.getString(R.string.ChooseAppearance))
        alert.setSingleChoiceItems(list, -1) {dialog, choose ->
            if (choose == 0){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("Appearance_Current", true)
                editor.apply()
                recreate()
            }
            if (choose == 1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("Appearance_Current", false)
                editor.apply()
                recreate()
            }
            dialog.dismiss()
        }
        var build = alert.create()
        build.show()
    }

    //Function Load Appearance
    private fun loadAppearance(){
        var sp = getSharedPreferences("Appearance", Activity.MODE_PRIVATE)
        var editor = sp.getBoolean("Appearance_Current", false)
        if (editor){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}