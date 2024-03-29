package kg.mirlan.fastfood.custom

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class StartSnapHelper : LinearSnapHelper() {

    private var verticalHelper: OrientationHelper? = null
    private var horizontalHelper: OrientationHelper? = null

    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            val helper = getHorizontalHelper(layoutManager)
            out[0] = getDistance(targetView, helper)
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()) {
            val helper = getVerticalHelper(layoutManager)
            out[1] = getDistance(targetView, helper)
        } else {
            out[1] = 0
        }

        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        var snapView: View? = null
        if (layoutManager is LinearLayoutManager) {
            val helper = getHorizontalHelper(layoutManager)
            snapView = findStartView(layoutManager, helper)
        }

        return snapView
    }

    private fun getDistance(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    /**
     * Returns the first view that we should snap to.
     *
     * @param layoutManager the recyclerview's layout manager
     * @param helper        orientation helper to calculate view sizes
     * @return the first view in the LayoutManager to snap to
     */
    private fun findStartView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper): View? {
        if (layoutManager is LinearLayoutManager) {
            val firstChild = layoutManager.findFirstVisibleItemPosition()
            if (firstChild == RecyclerView.NO_POSITION) return null

            val child = layoutManager.findViewByPosition(firstChild)

            // We should return the child if it's visible width
            // is greater than 0.5 of it's total width.
            val visibleWidth = helper.getDecoratedEnd(child).toFloat() / helper.getDecoratedMeasurement(child)

            // If we're at the end of the list, we shouldn't snap
            // to avoid having the last item not completely visible.
            val endOfList = layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1

            return if (visibleWidth > 0.5f && !endOfList) {
                child
            } else if (endOfList) {
                null
            } else {
                // If the child wasn't returned, we need to return
                // the next view close to the start.
                layoutManager.findViewByPosition(firstChild + 1)
            }
        }

        return null
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return verticalHelper ?: OrientationHelper.createVerticalHelper(layoutManager)
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return horizontalHelper ?: OrientationHelper.createHorizontalHelper(layoutManager)
    }
}