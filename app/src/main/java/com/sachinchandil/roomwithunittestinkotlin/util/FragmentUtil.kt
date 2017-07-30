package com.ace.diettracker.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * Created by sachin on 28/5/17.
 */
fun <T: ViewModel> Fragment.initViewModel(c:Class<T>):T{
    val model = ViewModelProviders.of(this).get(c)
    return model
}

fun Fragment.loadFragment(containerId:Int, fragment: Fragment){
    loadFragment(containerId, fragment, activity as AppCompatActivity)
}

fun loadFragment(containerId:Int, fragment: Fragment, activity: AppCompatActivity){
    activity.supportFragmentManager.beginTransaction().replace(containerId, fragment)
            .addToBackStack(null).commit()
}

fun Fragment.setSupportActionBar(toolbar: Toolbar){
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
}

fun Fragment.setDisplayHomeAsUpEnabled(value:Boolean){
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(value)
}

fun Fragment.getAppCompactActivity():AppCompatActivity{
    return activity as AppCompatActivity
}

