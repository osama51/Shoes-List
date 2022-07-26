package com.example.shoestore_starter

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.addFloatingButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoesDetailsFragment())
            Toast.makeText(activity, "You Pressed Me", Toast.LENGTH_LONG).show()
        }

        val tempCard = binding.templateCarView
        val myLinearLayout: LinearLayout = binding.shoesListLinearlayout
        val layoutParams: ViewGroup.LayoutParams = tempCard!!.layoutParams

        val myShoe = LinearLayout(activity)
        myShoe.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        var cardHeight = dpToPx(100)
        var cardMargin = dpToPx(5)
        myShoe.layoutParams.height = cardHeight.toInt()
        setMargins(myShoe, cardMargin, cardMargin, cardMargin, cardMargin)
        myShoe.background = ContextCompat.getDrawable(context!!, R.drawable.card_background)
        myShoe.orientation = LinearLayout.HORIZONTAL
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100.toFloat(),
            resources.displayMetrics
        ).toInt()
        /* ########## DON'T MIND ME #############

        // for later reference
//        myShoe.setBackgroundColor(Color.parseColor("#000000"))
//        myShoe.setBackgroundColor(ContextCompat.getDrawable(context, R.drawable.card_background))
//        myShoe.background = Drawable.createFromPath("@drawable/card_background")

        ######################################### */

//        myShoe.setTextColor(
//            Color.parseColor("#bdbdbd"))

//        myLinearLayout.removeAllViews()
        myLinearLayout.addView(myShoe)
        return binding.root
    }

    private fun dpToPx(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            resources.displayMetrics
        ).toInt()

    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}

