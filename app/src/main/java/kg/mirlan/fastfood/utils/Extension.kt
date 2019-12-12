package kg.mirlan.fastfood.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.firestore.*

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T> diffItemCallback(
    contentsSame: (Pair<T, T>) -> Boolean = { it.first == it.second },
    itemsSame: (Pair<T, T>) -> Boolean
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = itemsSame(Pair(oldItem, newItem))
    override fun areContentsTheSame(oldItem: T, newItem: T) = contentsSame(Pair(oldItem, newItem))
}
inline fun <reified T> DocumentReference.asLiveData() =
    toLiveData { it?.toObject(T::class.java) }

fun <T> DocumentReference.toLiveData(convert: (DocumentSnapshot?) -> T?) = object : LiveData<T?>() {
    var listener: ListenerRegistration? = null

    override fun onActive() {
        super.onActive()
        listener = addSnapshotListener { snapshot, _ ->
            value = convert(snapshot)
        }
    }

    override fun onInactive() {
        super.onInactive()
        listener?.remove()
        listener = null
    }
}

inline fun <reified T> CollectionReference.asLiveData() =
    toLiveData { it?.map { it?.toObject(T::class.java) } }

fun <T> Query.toLiveData(convert: (QuerySnapshot?) -> T?) = object : LiveData<T?>() {
    var listener: ListenerRegistration? = null

    override fun onActive() {
        super.onActive()
        listener = addSnapshotListener { snapshot, _ ->
            value = convert(snapshot)
        }
    }

    override fun onInactive() {
        super.onInactive()
        listener?.remove()
        listener = null
    }
}

fun <T : Any?> MutableLiveData<T>.update(update: (T?) -> T?) {
    value = update(value)
}