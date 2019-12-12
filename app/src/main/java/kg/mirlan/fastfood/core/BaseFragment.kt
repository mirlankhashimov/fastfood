package kg.mirlan.fastfood.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.base_appbar.*

abstract class BaseFragment(@LayoutRes val layoutRes: Int) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutRes, container, false)
    }

    open fun initToolbar(title: String?, toolbar: Toolbar? = core_toolbar) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                if (title != null) setTitle(title)
            }
        }
    }
    fun getAssetFiles(fileName: String): String {
        try {
            val inputStream = context?.assets?.open(fileName)
            val size: Int? = inputStream?.available()
            val buffer = ByteArray(size ?: 0)
            inputStream?.read(buffer)
            inputStream?.close()
            return String(buffer)
        } catch (e: Exception) { }
        return ""
    }

}