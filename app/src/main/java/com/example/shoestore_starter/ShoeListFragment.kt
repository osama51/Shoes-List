package com.example.shoestore_starter

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.shoestore_starter.databinding.FragmentShoeListBinding


class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ActivityViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)


        setHasOptionsMenu(true)
        binding.addFloatingButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoesDetailsFragment())
            Toast.makeText(activity, "You Pressed Me", Toast.LENGTH_SHORT).show()
        }

        /**     to implement a menu in a specific fragment
         *      and not in an activity .. I had my toolbar
         *      moved to a fragment, so I couldn't use
         *      setSupportActionBar(binding.toolbar)        */

////         reference: https://stackoverflow.com/questions/8308695/how-to-add-options-menu-to-fragment-in-android#:~:text=onOptionsItemSelected(it)%0A%7D-,Standalone%20Toolbar,-Most%20of%20the
//        val toolbar = binding.toolbar
//        toolbar.inflateMenu(R.menu.overflow_menu)
//        toolbar.setOnMenuItemClickListener {
//            onOptionsItemSelected(it)
//        }

        viewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoesList.observe(viewLifecycleOwner, Observer {

            for (i in viewModel.shoesList.value!!) {
                if(viewModel.flag.value==true){
                    val myLinearLayout: LinearLayout = binding.shoesListLinearlayout
                    var padding = dpToPx(10)
                    var cardHeight = dpToPx(100)
                    var cardMargin = dpToPx(5)

                    /** another way to get params from a ref view */
                    // val tempCard = binding.templateCarView
                    // val layoutParams: ViewGroup.LayoutParams = tempCard!!.layoutParams

                    /**
                     *      Starting from here, we are addViewing
                     *      until you hit "return binding.root"         */

                    /** Create the model outer Layout, which contains 2 columns (img column + info column) */
                    val myShoeCard = LinearLayout(activity)
                    myShoeCard.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    myShoeCard.layoutParams.height = cardHeight
                    myShoeCard.setPadding(padding, padding, padding, padding)
                    setMargins(myShoeCard, cardMargin, cardMargin, cardMargin, cardMargin)

                    myShoeCard.background =
                        ContextCompat.getDrawable(context!!, R.drawable.card_background)
                    myShoeCard.orientation = LinearLayout.HORIZONTAL

                    /** Create a vertical Linearlayout that holds the data of the shoe model */
                    val myShoeData = LinearLayout(activity)
                    myShoeData.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    setMargins(myShoeData, 50, 0, 20, 0)
                    myShoeData.orientation = LinearLayout.VERTICAL

                    /** 4 Textviews for the data to be displayed */
                    val shoeName = TextView(activity)
                    shoeName.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    val shoeCompany = TextView(activity)
                    shoeCompany.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    val shoeSize = TextView(activity)
                    shoeSize.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    val shoeDescription = TextView(activity)
                    shoeDescription.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    /** Setting common attributes to all 4 Textviews */
                    var arrayOfTextViews: Array<TextView> = arrayOf(shoeName, shoeCompany, shoeSize, shoeDescription)

                    for (i in arrayOfTextViews){
                        i.setTextColor(Color.parseColor("#FFFFFF"))
                        i.setPadding(0, 0, 0, dpToPx(2))
                        i.setTypeface(null, Typeface.BOLD);
                    }

                    /**  added a cardview to have a round cornered image */
                    val shoeCard = activity?.let { CardView(it) }
                    shoeCard?.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    shoeCard?.layoutParams?.height = dpToPx(80)
                    shoeCard?.layoutParams?.width = dpToPx(80)
                    shoeCard?.radius = dpToPx(10).toFloat()

                    /** The image to be added to the CardView*/
                    val shoeCardImage = ImageView(activity)
                    shoeCardImage.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                    shoeCardImage.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    shoeCardImage.setImageResource(R.drawable.ic_shoe_)
                    shoeCardImage.background =
                        ContextCompat.getDrawable(context!!, R.drawable.shoe_corners)


                    /** ########## DON'T MIND ME #############

                    // for later reference
                    myShoe.setBackgroundColor(Color.parseColor("#000000"))
                    myShoe.setBackgroundColor(ContextCompat.getDrawable(context, R.drawable.card_background))
                    myShoe.background = Drawable.createFromPath("@drawable/card_background")

                    ######################################### */

//                /** i is an iterator from the for loop above, which iterates over the shoelist elements */
//                shoeName.text = ("Name: " + viewModel.shoesList[i].name.toString()) ?: ""
//                shoeCompany.text = ("Company: " + viewModel.shoesList[i].company.toString()) ?: ""
//                shoeSize.text = ("Size: " + viewModel.shoesList[i].size.toString()) ?: ""
//                shoeDescription.text =
//                    ("Description: " + viewModel.shoesList[i].description.toString()) ?: ""

                    /** i is an iterator from the for loop above, which iterates over the shoelist elements */
                    shoeName.text = ("Name: " + i.name.toString()) ?: ""
                    shoeCompany.text = ("Company: " + i.company.toString()) ?: ""
                    shoeSize.text = ("Size: " + i.size.toString()) ?: ""
                    shoeDescription.text =
                        ("Description: " + i.description.toString()) ?: ""

                    /** putting everything together */
                    myLinearLayout.addView(myShoeCard)
                    myShoeCard.addView(shoeCard)
                    myShoeCard.addView(myShoeData)
                    myShoeData.addView(shoeName)
                    myShoeData.addView(shoeCompany)
                    myShoeData.addView(shoeSize)
                    myShoeData.addView(shoeDescription)
                    shoeCard?.addView(shoeCardImage)
                }
            }
        })


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.log_out_menu_item -> {
                view?.findNavController()?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /** I was first trying to create the toolbar only in the shoe list screen (lines 54 - 59 and 198 - 206)
     * which took forever just trying to figure out why I'm doing this in the first place instead of adding it to the main activity,
     * finally I decided to add it back to the main activity while using hide() and show() methods  */
    override fun onResume() {
        super.onResume();
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Shoes List"
    }
    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    /** converts dp units to pixels.. since adding view dynamically takes args in pixels*/
    private fun dpToPx(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            resources.displayMetrics
        ).toInt()

    }

    /** a nice function that saves you 4 assignments of margins for every single view by gathering all margins in one function*/
    // reference: https://stackoverflow.com/questions/12728255/in-android-how-do-i-set-margins-in-dp-programmatically#:~:text=148-,Best%20way%20ever%3A,-private%20void%20setMargins
    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }

}

