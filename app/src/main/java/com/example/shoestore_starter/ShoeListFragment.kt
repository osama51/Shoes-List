package com.example.shoestore_starter

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
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

//        val tempCard = binding.templateCarView
        val myLinearLayout: LinearLayout = binding.shoesListLinearlayout
//        val layoutParams: ViewGroup.LayoutParams = tempCard!!.layoutParams

        val myShoeCard = LinearLayout(activity)
        myShoeCard.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        var padding = dpToPx(10)
        myShoeCard.setPadding(padding,padding,padding,padding)

        var cardHeight = dpToPx(100)
        var cardMargin = dpToPx(5)

        myShoeCard.layoutParams.height = cardHeight.toInt()
        setMargins(myShoeCard, cardMargin, cardMargin, cardMargin, cardMargin)
        myShoeCard.background = ContextCompat.getDrawable(context!!, R.drawable.card_background)
        myShoeCard.orientation = LinearLayout.HORIZONTAL

        val shoeCard = activity?.let { CardView(it) }
        shoeCard?.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        shoeCard?.layoutParams?.height = dpToPx(80)
        shoeCard?.layoutParams?.width = dpToPx(80)
        shoeCard?.radius = dpToPx(10).toFloat()

        val shoeCardImage = ImageView(activity)
        shoeCardImage.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
//        shoeCardImage.layoutParams.height = dpToPx(90)
//        shoeCardImage.layoutParams.width = dpToPx(90)
        shoeCardImage.setBackgroundColor(Color.parseColor("#FFFFFF"))
        shoeCardImage.setImageResource(R.drawable.ic_shoe_)
        shoeCardImage.background = ContextCompat.getDrawable(context!!, R.drawable.shoe_corners)


        /* ########## DON'T MIND ME #############

        // for later reference
//        myShoe.setBackgroundColor(Color.parseColor("#000000"))
//        myShoe.setBackgroundColor(ContextCompat.getDrawable(context, R.drawable.card_background))
//        myShoe.background = Drawable.createFromPath("@drawable/card_background")

        ######################################### */

//        myShoe.setTextColor(
//            Color.parseColor("#bdbdbd"))

        myLinearLayout.addView(myShoeCard)
        myShoeCard.addView(shoeCard)
        shoeCard?.addView(shoeCardImage)
        return binding.root
    }

    private fun dpToPx(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            resources.displayMetrics
        ).toInt()

    }

    // reference: https://stackoverflow.com/questions/12728255/in-android-how-do-i-set-margins-in-dp-programmatically#:~:text=148-,Best%20way%20ever%3A,-private%20void%20setMargins
    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}

