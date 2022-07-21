package com.techjd.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.marginTop
import com.google.android.material.button.MaterialButton

class OnBoardingFragment : Fragment() {
    private lateinit var backGround: ImageView
    private lateinit var title: TextView
    private lateinit var subTitle: TextView
    private lateinit var next: MaterialButton
    private lateinit var skip: TextView
    private lateinit var letsGetStarted: MaterialButton
    private lateinit var smallSubTitle: TextView
    private lateinit var scrollView: ScrollView
    private lateinit var indicator: ImageView
    private lateinit var progress: ImageView


    private lateinit var okText: TextView
    private lateinit var someAccess: TextView

    private lateinit var moneySafe: TextView
    private lateinit var iciciLL: LinearLayout

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = OnBoardingFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = requireArguments().getInt(ARG_POSITION)

        val backgrounds = arrayListOf(
            context!!.getDrawable(R.drawable.raw_bcg_1_tweak),
            context!!.getDrawable(R.drawable.raw_bcg_2_tweak),
            context!!.getDrawable(R.drawable.raw_bcg_3_tweak),
            context!!.getDrawable(R.drawable.raw_bcg_4_tweak)
        )

        val indicators = arrayListOf(
            context!!.getDrawable(R.drawable.ic_indicator_1),
            context!!.getDrawable(R.drawable.ic_indicator_2),
            context!!.getDrawable(R.drawable.ic_indicator_3),
            context!!.getDrawable(R.drawable.ic_indicator_3),
        )

        val progressList = arrayListOf(
            context!!.getDrawable(R.drawable.ic_first_progress),
            context!!.getDrawable(R.drawable.ic_second_progress),
            context!!.getDrawable(R.drawable.ic_last_progress),
            context!!.getDrawable(R.drawable.ic_last_progress),
        )

        val titles = arrayListOf(
            "Welcome to PayNav",
            "Spending is Investing",
            "A new age Gold rush begins",
            ""
        )

        val subTitles = arrayListOf(
            "PayNav, is the easy way to share gifts,\n" +
                    "expenses and experiences with friends & groups.",
            "Do your Regular Shopping with your fave brands and win money every time, invested as Gold in your portfolio.",
            "Save money in digital gold and get 9-11%* annualized returns. Plan major goals like a phone or a vacation and achieve them.",
            ""
        )


        title = view.findViewById(R.id.title)
        subTitle = view.findViewById(R.id.subTitle)
        backGround = view.findViewById(R.id.background)
        next = view.findViewById(R.id.continueBtn)
        skip = view.findViewById(R.id.skip)
        letsGetStarted = view.findViewById(R.id.letsGetStarted)
        smallSubTitle = view.findViewById(R.id.smallSubTitle)
        scrollView = view.findViewById(R.id.scrollView)
        indicator = view.findViewById(R.id.indicator)
        progress = view.findViewById(R.id.progress)

        okText = view.findViewById(R.id.okBlueText)
        someAccess = view.findViewById(R.id.access)
        moneySafe = view.findViewById(R.id.safeMoney)
        iciciLL = view.findViewById(R.id.icicBankLL)


        if (position == 0) {
            skip.visibility = View.VISIBLE
            letsGetStarted.visibility = View.VISIBLE
            indicator.visibility = View.VISIBLE
            progress.visibility = View.VISIBLE
        } else {
            skip.visibility = View.GONE
            letsGetStarted.visibility = View.GONE
            next.visibility = View.VISIBLE
        }

        if (position == 1) {
            indicator.visibility = View.VISIBLE
            progress.visibility = View.VISIBLE
        }

        if (position == 2) {
            smallSubTitle.visibility = View.VISIBLE
            indicator.visibility = View.VISIBLE
            progress.visibility = View.VISIBLE

        } else {
            smallSubTitle.visibility = View.GONE
        }

        backGround.background = backgrounds[position]
        title.text = titles[position]
        subTitle.text = subTitles[position]
        indicator.background = indicators[position]
        progress.background = progressList[position]

        if (position == 3) {
            backGround.background = backgrounds[position]
            title.visibility = View.GONE
            subTitle.visibility = View.GONE
            next.text = "ALLOW ACCESS"
            scrollView.visibility = View.VISIBLE
            indicator.visibility = View.GONE
            progress.visibility = View.GONE

            okText.visibility = View.VISIBLE
            someAccess.visibility = View.VISIBLE
            moneySafe.visibility = View.VISIBLE
            iciciLL.visibility = View.VISIBLE
        }

        letsGetStarted.setOnClickListener {
            (activity as MainActivity).nextPage()
        }





        next.setOnClickListener {
            (activity as MainActivity).nextPage()
        }

        skip.setOnClickListener {
            (activity as MainActivity).lastPage()
        }
    }
}