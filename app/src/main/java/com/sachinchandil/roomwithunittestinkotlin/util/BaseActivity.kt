package com.ace.diettracker.util

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by sachin on 27/6/17.
 */
open class BaseActivity : AppCompatActivity(), SimpleProgressDialog {
    override var progressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}