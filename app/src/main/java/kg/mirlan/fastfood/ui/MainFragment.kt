package kg.mirlan.fastfood.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kg.mirlan.fastfood.R
import kg.mirlan.fastfood.core.BaseFragment
import kg.mirlan.fastfood.utils.AppPreferences
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainFragment : BaseFragment(R.layout.main_fragment) {
    private lateinit var auth: FirebaseAuth
    private val appPreferences: AppPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if (appPreferences.userId.isNullOrBlank()) {
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.action_main_screen_to_register_screen)
            }
        } else {
            view?.let {
                Navigation.findNavController(it).navigate(R.id.action_main_screen_to_home_screen)
            }
        }

    }
}