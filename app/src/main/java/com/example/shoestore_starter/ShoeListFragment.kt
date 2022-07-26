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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentShoeListBinding
import com.example.shoestore_starter.modeks.ShoeDetailViewModel

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.addFloatingButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoesDetailsFragment())
            Toast.makeText(activity, "You Pressed Me", Toast.LENGTH_SHORT).show()
        }

        viewModel = ViewModelProvider(requireActivity())[ShoeDetailViewModel::class.java]

        viewModel.flag.observe(viewLifecycleOwner, Observer{
            val myLinearLayout: LinearLayout = binding.shoesListLinearlayout

            // another way to get params from a ref view
//        val tempCard = binding.templateCarView
//        val layoutParams: ViewGroup.LayoutParams = tempCard!!.layoutParams

            val myShoeCard = LinearLayout(activity)
            myShoeCard.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            myShoeCard.isClickable = true
            myShoeCard.isFocusable = true
            var padding = dpToPx(10)
            myShoeCard.setPadding(padding, padding, padding, padding)

            var cardHeight = dpToPx(100)
            var cardMargin = dpToPx(5)

            myShoeCard.layoutParams.height = cardHeight.toInt()
            setMargins(myShoeCard, cardMargin, cardMargin, cardMargin, cardMargin)
            myShoeCard.background = ContextCompat.getDrawable(context!!, R.drawable.card_background)
            myShoeCard.orientation = LinearLayout.HORIZONTAL

            val myShoeData = LinearLayout(activity)
            myShoeData.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setMargins(myShoeData, 50, 0, 20, 0)
            myShoeData.orientation = LinearLayout.VERTICAL

            val shoeName = TextView(activity)
            shoeName.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            shoeName.setPadding(0, 0, 0, dpToPx(2))

            val shoeCompany = TextView(activity)
            shoeCompany.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            shoeCompany.setPadding(0, 0, 0, dpToPx(2))

            val shoeSize = TextView(activity)
            shoeSize.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            shoeSize.setPadding(0, 0, 0, dpToPx(2))

            val shoeDescription = TextView(activity)
            shoeDescription.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            shoeName.setTextColor(Color.parseColor("#000000"))
            shoeCompany.setTextColor(Color.parseColor("#000000"))
            shoeSize.setTextColor(Color.parseColor("#000000"))
            shoeDescription.setTextColor(Color.parseColor("#000000"))

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
//

            shoeName.text = ("Name: " + viewModel.newShoe.value?.name) ?: ""
            shoeCompany.text = ("Company: " + viewModel.newShoe.value?.company) ?: ""
            shoeSize.text = "Size: " +viewModel.newShoe.value?.size.toString()
            shoeDescription.text = ("Description: " + viewModel.newShoe.value?.description) ?: ""

            myLinearLayout.addView(myShoeCard)
            myShoeCard.addView(shoeCard)
            myShoeCard.addView(myShoeData)
            myShoeData.addView(shoeName)
            myShoeData.addView(shoeCompany)
            myShoeData.addView(shoeSize)
            myShoeData.addView(shoeDescription)
            shoeCard?.addView(shoeCardImage)

        })

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

