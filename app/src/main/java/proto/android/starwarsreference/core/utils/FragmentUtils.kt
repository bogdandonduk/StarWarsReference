package proto.android.starwarsreference.core.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {
    fun loadFragment(fm: FragmentManager, fragment: Fragment, tag: String, containerView: View) {
        if(fm.findFragmentById(containerView.id) != null)
            fm.beginTransaction().replace(containerView.id, fragment, tag).commit()
        else
            fm.beginTransaction().add(containerView.id, fragment, tag).commit()
    }
}