package com.trendyol.uicomponents

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.trendyol.uicomponents.quantitypickerview.ExpansionState
import com.trendyol.uicomponents.quantitypickerview.QuantityPickerView
import com.trendyol.uicomponents.quantitypickerview.QuantityPickerViewState
import kotlinx.android.synthetic.main.activity_quantity_picker_view.*

class QuantityPickerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quantity_picker_view)

        val viewState = QuantityPickerViewState(
            text = "Fresh Money",
            textSize = asSP(12),
            quantityTextSize = asSP(14),
            backgroundDrawable = drawable(R.drawable.qpv_shape_default_background),
            removeIconDrawable = drawable(R.drawable.qpv_ic_default_remove),
            subtractIconDrawable = drawable(R.drawable.qpv_ic_default_subtract),
            addIconDrawable = drawable(R.drawable.qpv_ic_default_add),
            quantityBackgroundDrawable = drawable(R.drawable.qpv_shape_default_background),
            textColor = themeColor(R.attr.colorAccent),
            progressTintColor = themeColor(R.attr.colorAccent),
            quantityTextColor = themeColor(R.attr.colorPrimary),
            buttonHorizontalPadding = asDP(8),
            buttonVerticalPadding = asDP(8),
            progressVerticalPadding = asDP(6),
            quantityBackgroundVerticalPadding = asDP(6)
        )

        quantity_picker_view_2.setQuantityPickerViewState(viewState)

        with(quantity_picker_view) {
            onAddClicked = { number ->
                Toast.makeText(context, "Add click: $number", Toast.LENGTH_SHORT).show()
                completeLoading(this, increment = +1)
                true
            }
            onSubtractClicked = { number ->
                Toast.makeText(context, "Subtract/Remove click: $number", Toast.LENGTH_SHORT).show()
                false
            }

        }
        with(quantity_picker_view_collapsed_right) {
            onAddClicked = { number ->
                Toast.makeText(context, "Add click: $number", Toast.LENGTH_SHORT).show()
                completeLoading(this, increment = +1)
                true
            }
            onSubtractClicked = { number ->
                Toast.makeText(context, "Subtract/Remove click: $number", Toast.LENGTH_SHORT).show()
                completeLoading(this, increment = -1)
                true
            }
            expansionListener = {
                Toast.makeText(context, "${it.toString()} ${it.expanded}", Toast.LENGTH_LONG).show()
            }
        }
        button_reset.setOnClickListener {
            quantity_picker_view.reset()
            quantity_picker_view_2.reset()
            quantity_picker_view_collapsed.reset()
        }
    }

    private fun completeLoading(quantityPickerView: QuantityPickerView, increment: Int, delay: Long = 500L) {
        quantityPickerView.postDelayed({
            quantityPickerView.incrementQuantityBy(increment)
        }, delay)
    }
}
