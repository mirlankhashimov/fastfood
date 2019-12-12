package kg.mirlan.fastfood.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kg.mirlan.fastfood.model.Category
import kg.mirlan.fastfood.model.Shop
import kg.mirlan.fastfood.model.User
import kg.mirlan.fastfood.utils.ApiRoutes
import kg.mirlan.fastfood.utils.AppPreferences
import kg.mirlan.fastfood.utils.asLiveData
import timber.log.Timber

class HomeRepository(
    private val firestore: FirebaseFirestore,
    private val appPreferences: AppPreferences
) {
    fun saveUser(user: FirebaseUser?) {
        val userMap = hashMapOf("email" to user?.email, "name" to user?.displayName)
        firestore.collection(ApiRoutes.USER).document(user?.uid ?: "").set(userMap)
            .addOnSuccessListener {
                appPreferences.userId = user?.uid
            }
    }

    fun addShop(shop: Shop) {
        val shop1 = hashMapOf("name" to shop.name, "password" to shop.password)
        getShopsRef().document(shop.name ?: "").set(shop1).addOnCompleteListener {}
    }

    fun getShops() = getShopsRef().asLiveData<Shop>()

    fun saveCategory(name: String) {
        val cat = hashMapOf("name" to name)
        getCategoryRef().document(name)
            .set(cat).addOnCompleteListener {
                Timber.e("ok")
            }.addOnFailureListener {
                Timber.e(it.message)
            }
    }

    fun getCategory() = getCategoryRef().asLiveData<Category>()

    fun deleteCategory(name: String) {
        getCategoryRef().document(name).delete()
            .addOnSuccessListener {
                Timber.e("deleted")
            }
            .addOnFailureListener {

            }
    }

    private fun getProfileRef() = firestore
        .collection(ApiRoutes.USER)
        .document(appPreferences.userId ?: "")

    private fun getShopsRef() = getProfileRef().collection(ApiRoutes.SHOPS)

    private fun getCategoryRef() =
        getShopsRef().document(appPreferences.shopNameKey ?: "").collection(ApiRoutes.CATEGORIES)
}