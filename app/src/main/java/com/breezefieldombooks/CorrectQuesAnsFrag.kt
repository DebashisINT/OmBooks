package com.breezefieldombooks

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.breezefieldombooks.app.NetworkConstant
import com.breezefieldombooks.app.Pref
import com.breezefieldombooks.app.utils.AppUtils
import com.breezefieldombooks.base.presentation.BaseActivity
import com.breezefieldombooks.base.presentation.BaseFragment
import com.breezefieldombooks.features.dashboard.presentation.DashboardActivity
import com.breezefieldombooks.features.mylearning.ContentL
import com.breezefieldombooks.features.mylearning.CorrectQuestionAnswer
import com.breezefieldombooks.features.mylearning.MyTopicsWiseContents
import com.breezefieldombooks.features.mylearning.Option
import com.breezefieldombooks.features.mylearning.Question_answer_fetch_list
import com.breezefieldombooks.features.mylearning.RetryCorrectQuestionAnswerAdapter
import com.breezefieldombooks.features.mylearning.RetryIncorrectQuizFrag
import com.breezefieldombooks.features.mylearning.TopicContentWiseAnswerListsFetchResponse
import com.breezefieldombooks.features.mylearning.VideoTopicWiseResponse
import com.breezefieldombooks.features.mylearning.apiCall.LMSRepoProvider
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.ArrayList


class CorrectQuesAnsFrag : BaseFragment() , View.OnClickListener{

    private lateinit var correctAnswers: MutableList<String>
    private lateinit var incorrectAnswers: MutableList<String>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RetryCorrectQuestionAnswerAdapter
    private lateinit var mContext: Context
    lateinit var progress_wheel: ProgressWheel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
        var topicId: String = ""
        var storeContentId: String = ""

        fun getInstance(topicId: String, storeContentId: String): CorrectQuesAnsFrag {
            val fragment = CorrectQuesAnsFrag()
            this.topicId = topicId
            this.storeContentId = storeContentId
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_correct_ques_ans, container, false)
        initView(view)
        println("Topic ID: $topicId")
        println("Store Content ID: $storeContentId")
        return view
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rv_correct_answer_tab)
        progress_wheel = view.findViewById(R.id.ll_frag_my_learning_progress_wheel)

        recyclerView.layoutManager = LinearLayoutManager(mContext)

        getTopicContentWiseAnswerListsAPICalling()

        val sampleQuestionAnswers = listOf(
            CorrectQuestionAnswer(
                questionId = 1,
                question = "What is a major factor driving changes in consumer behavior in the CPG market?",
                answered = "optionNo2",
                optionList = listOf(
                    Option(
                        optionId = 1,
                        optionNo1 = "Decreased product availability",
                        optionPoint1 = 0,
                        isCorrect1 = false,
                        optionNo2 = "Increased health consciousness",
                        optionPoint2 = 5,
                        isCorrect2 = true,
                        optionNo3 = "Reduced marketing efforts",
                        optionPoint3 = 0,
                        isCorrect3 = false,
                        optionNo4 = "Limited access to information",
                        optionPoint4 = 0,
                        isCorrect4 = false
                    )
                )
            ),
            CorrectQuestionAnswer(
                questionId = 2,
                question = "Which of the following is a common trend in the CPG industry?",
                answered = "optionNo1",
                optionList = listOf(
                    Option(
                        optionId = 2,
                        optionNo1 = "Increased online shopping",
                        optionPoint1 = 5,
                        isCorrect1 = true,
                        optionNo2 = "Decreased product variety",
                        optionPoint2 = 0,
                        isCorrect2 = false,
                        optionNo3 = "Reduced consumer loyalty",
                        optionPoint3 = 0,
                        isCorrect3 = false,
                        optionNo4 = "Higher prices across the board",
                        optionPoint4 = 0,
                        isCorrect4 = false
                    )
                )
            ),
            CorrectQuestionAnswer(
                questionId = 3,
                question = "What influences consumer decisions the most?",
                answered = "optionNo1",
                optionList = listOf(
                    Option(
                        optionId = 3,
                        optionNo1 = "Brand reputation",
                        optionPoint1 = 5,
                        isCorrect1 = true,
                        optionNo2 = "Product packaging",
                        optionPoint2 = 0,
                        isCorrect2 = false,
                        optionNo3 = "Celebrity endorsements",
                        optionPoint3 = 0,
                        isCorrect3 = false,
                        optionNo4 = "Store location",
                        optionPoint4 = 0,
                        isCorrect4 = false
                    )
                )
            ),
            CorrectQuestionAnswer(
                questionId = 4,
                question = "What is a key driver of sustainability in the CPG sector?",
                answered = "optionNo1",
                optionList = listOf(
                    Option(
                        optionId = 4,
                        optionNo1 = "Use of biodegradable packaging",
                        optionPoint1 = 5,
                        isCorrect1 = true,
                        optionNo2 = "Increased advertising",
                        optionPoint2 = 0,
                        isCorrect2 = false,
                        optionNo3 = "Higher production costs",
                        optionPoint3 = 0,
                        isCorrect3 = false,
                        optionNo4 = "Limited product range",
                        optionPoint4 = 0,
                        isCorrect4 = false
                    )
                )
            )
        )

        adapter = RetryCorrectQuestionAnswerAdapter(sampleQuestionAnswers ,mContext)
        recyclerView.adapter = adapter
    }

    private fun getTopicContentWiseAnswerListsAPICalling() {

        try {
            progress_wheel.visibility = View.VISIBLE
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopicContentWiseAnswerLists(Pref.user_id!!, topicId , storeContentId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicContentWiseAnswerListsFetchResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            progress_wheel.visibility = View.GONE

                            try {
                                if (response.question_answer_fetch_list != null && response.question_answer_fetch_list.size > 0) {


                                } else {
                                    progress_wheel.visibility = View.GONE
                                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                                }
                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }
                        } else {
                            progress_wheel.visibility = View.GONE
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))

                        }
                    }, { error ->
                        println("errortopicwiselist"+error.message)
                        progress_wheel.visibility = View.GONE
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            progress_wheel.visibility = View.GONE
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }

    }

    private fun separateCorrectAndIncorrectAnswers(questionAnswerFetchList: ArrayList<Question_answer_fetch_list>): Pair<List<Question_answer_fetch_list>, List<Question_answer_fetch_list>> {

        val correctAnswers = mutableListOf<Question_answer_fetch_list>()
        val incorrectAnswers = mutableListOf<Question_answer_fetch_list>()

        for (questionAnswer in questionAnswerFetchList) {
            val selectedAnswer = questionAnswer.answered
            var isAnswerCorrect = false

            for (option in questionAnswer.option_list) {
                when (selectedAnswer) {
                    option.option_no_1 -> isAnswerCorrect = option.isCorrect_1
                    option.option_no_2 -> isAnswerCorrect = option.isCorrect_2
                    option.option_no_3 -> isAnswerCorrect = option.isCorrect_3
                    option.option_no_4 -> isAnswerCorrect = option.isCorrect_4
                }
            }

            if (isAnswerCorrect) {
                correctAnswers.add(questionAnswer)
            } else {
                incorrectAnswers.add(questionAnswer)
            }
        }

        return Pair(correctAnswers, incorrectAnswers)
    }



    override fun onClick(v: View?) {


    }

}