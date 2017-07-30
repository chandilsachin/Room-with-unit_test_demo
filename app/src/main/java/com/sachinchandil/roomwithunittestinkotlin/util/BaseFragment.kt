package com.ace.diettracker.util

import android.app.ProgressDialog
import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.MenuItem
import android.widget.ProgressBar
import com.ace.diettracker.util.annotation.RequiresTagName

/**
 * Created by sachin on 28/5/17.
 */
open class BaseFragment : LifecycleFragment(), SimpleProgressDialog {
    override var progressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                val fm = activity.supportFragmentManager
                if (fm.backStackEntryCount > 0) fm.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        val TAG_NAME = (BaseFragment::class.annotations.find { it == RequiresTagName::class } as? RequiresTagName)?.tagName

        fun findInstance(activity: FragmentActivity?): Fragment? {
            if(activity == null) return null
            return activity.supportFragmentManager.findFragmentByTag(TAG_NAME)
        }
    }

}