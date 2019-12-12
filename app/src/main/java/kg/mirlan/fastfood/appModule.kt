package kg.mirlan.fastfood

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kg.mirlan.fastfood.repository.HomeRepository
import kg.mirlan.fastfood.ui.categories.CategoriesViewModel
import kg.mirlan.fastfood.ui.shop.AddShopViewModel
import kg.mirlan.fastfood.ui.home.HomeViewModel
import kg.mirlan.fastfood.ui.shop.ShopDialogViewModel
import kg.mirlan.fastfood.ui.profile.RegisterViewModel
import kg.mirlan.fastfood.utils.AppPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { AppPreferences(get()) }
    single { HomeRepository(get(),get()) }
    single { get<Context>().resources }
    single {
        FirebaseFirestore.getInstance().apply {
            firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
        }
    }
    factory { FirebaseAuth.getInstance().currentUser?.uid ?: "" }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { AddShopViewModel(get()) }
    viewModel { ShopDialogViewModel(get()) }
    viewModel { CategoriesViewModel(get()) }
}