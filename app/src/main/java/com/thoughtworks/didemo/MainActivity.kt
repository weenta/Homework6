package com.thoughtworks.didemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    //why we need use factory to create viewmodel, can see ComponentActivity#onRetainNonConfigurationInstance
    // (shit+cmd+o to find ComponentActivity)
    private val viewModel: MyViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        (application as MyApplication).component.inject(this)

        click.setOnClickListener {
            viewModel.load()
            if( click.text != "Refresh") {
                click.text = "Refresh"
            }
        }
        viewModel.result.observe(this, Observer {
            when (it) {
                is ViewState.Show -> show(it.txt)
                is ViewState.Hide -> hide()
            }
        })
    }



    fun show(content: String) {
        txt_view.visibility = View.VISIBLE
        txt_view.text = content
    }


    fun hide() {
        Log.d("mainactivity","hide")
        txt_view.visibility = View.GONE
    }

}