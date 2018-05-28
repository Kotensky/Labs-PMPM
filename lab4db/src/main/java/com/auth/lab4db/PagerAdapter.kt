package com.auth.lab4db

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.auth.lab4db.fragments.AddFragment
import com.auth.lab4db.fragments.DeleteFragment
import com.auth.lab4db.fragments.ShowFragment
import com.auth.lab4db.fragments.UpdateFragment

class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            1 -> AddFragment()
            2 -> DeleteFragment()
            3 -> UpdateFragment()
            else -> ShowFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            1 -> "Add"
            2 -> "Delete"
            3 -> "Update"
            else -> "Show"
        }
    }
}