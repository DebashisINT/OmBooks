package com.breezefieldombooks.features.performanceAPP

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.breezefieldombooks.DialogLoading
import com.breezefieldombooks.R
import com.breezefieldombooks.app.AppDatabase
import com.breezefieldombooks.app.NetworkConstant
import com.breezefieldombooks.app.Pref
import com.breezefieldombooks.base.presentation.BaseActivity
import com.breezefieldombooks.base.presentation.BaseFragment
import com.breezefieldombooks.features.contacts.CustomData
import com.breezefieldombooks.features.contacts.GenericDialog
import com.breezefieldombooks.features.contacts.TargetLevelResponse
import com.breezefieldombooks.features.contacts.TargetTypeDtls
import com.breezefieldombooks.features.contacts.TargetTypeResponse
import com.breezefieldombooks.features.contacts.TypeMasterRes
import com.breezefieldombooks.features.dashboard.presentation.DashboardActivity
import com.breezefieldombooks.features.performanceAPP.model.ChartDataModelNew
import com.breezefieldombooks.features.shopdetail.presentation.api.EditShopRepoProvider
import com.bumptech.glide.Glide
import com.ekn.gruzer.gaugelibrary.HalfGauge
import com.ekn.gruzer.gaugelibrary.Range
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TargetVSAchvFrag: BaseFragment(), View.OnClickListener  {

    private lateinit var ivTargetPointer : ImageView
    private lateinit var ivAchvPointer : ImageView
    private lateinit var llTargetType : LinearLayout
    private lateinit var llTargetLevel : LinearLayout
    private lateinit var tvTargetType : TextView
    private lateinit var tvTargetLevel : TextView

    private lateinit var rgTimeFrame:RadioGroup
    private lateinit var rbTimeFrame_daily:RadioButton
    private lateinit var rbTimeFrame_weekly:RadioButton
    private lateinit var rbTimeFrame_monthly:RadioButton
    private lateinit var rbTimeFrame_yearly:RadioButton

    private lateinit var targetTypeL :ArrayList<TargetTypeDtls>
    private lateinit var targetLevelL :ArrayList<TargetTypeDtls>

    private var selectedTargetType :String = ""
    private var selectedTargetLevel :String = ""
    private var selectedTimeFrame :String = ""

    private lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.frag_target_vs_achv, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        ivTargetPointer = view.findViewById(R.id.iv_frag_ta_target_pointer)
        ivAchvPointer = view.findViewById(R.id.iv_frag_ta_achv_pointer)
        llTargetType = view.findViewById(R.id.ll_frag_ta_target_type)
        llTargetLevel = view.findViewById(R.id.ll_frag_ta_target_level)
        tvTargetType = view.findViewById(R.id.tv_frag_ta_target_type)
        tvTargetLevel = view.findViewById(R.id.tv_frag_ta_target_level)

        rgTimeFrame = view.findViewById(R.id.rg_frag_ta_time_frame)
        rbTimeFrame_daily = view.findViewById(R.id.rb_frag_ta_time_frame_daily)
        rbTimeFrame_weekly = view.findViewById(R.id.rb_frag_ta_time_frame_weekly)
        rbTimeFrame_monthly = view.findViewById(R.id.rb_frag_ta_time_frame_monthly)
        rbTimeFrame_yearly = view.findViewById(R.id.rb_frag_ta_time_frame_yearly)

        rgTimeFrame.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                rbTimeFrame_daily.id -> {
                    selectedTimeFrame = "D"
                    callDtlsApi()
                }
                rbTimeFrame_weekly.id -> {
                    selectedTimeFrame = "W"
                    callDtlsApi()
                }
                rbTimeFrame_monthly.id -> {
                    selectedTimeFrame = "M"
                    callDtlsApi()
                }
                rbTimeFrame_yearly.id -> {
                    selectedTimeFrame = "Y"
                    callDtlsApi()
                }
            }
        }

        llTargetType.setOnClickListener(this)
        llTargetLevel.setOnClickListener(this)

        Glide.with(mContext)
            .load(R.drawable.icon_pointer_gif)
            .into(ivTargetPointer)

        Glide.with(mContext)
            .load(R.drawable.icon_pointer_gif)
            .into(ivAchvPointer)

        var halfGauge = view.findViewById<HalfGauge>(R.id.half_gauge)

        halfGauge.padding = 5f
        val range = Range()
        range.color = Color.parseColor("#10AD65") // green
        range.from =0.0
        range.to = 300.0

        val range2 = Range()
        range2.color = Color.parseColor("#E76B1F")
        range2.from = 300.0
        range2.to = 1000.0

        halfGauge.addRange(range)
        halfGauge.addRange(range2)

        //set min max and current value
        //set min max and current value
        halfGauge.minValue = 0.0
        halfGauge.maxValue = (1000).toDouble()
        halfGauge.value = (300).toDouble()

        halfGauge.setNeedleColor(Color.parseColor("#5E5E5E"))
        halfGauge.valueColor = Color.DKGRAY
        halfGauge.minValueTextColor = Color.BLACK
        halfGauge.maxValueTextColor = Color.BLACK

        halfGauge.isEnableNeedleShadow = true

        callTargetType()
    }

    private fun callTargetType(){
        DialogLoading.show(requireActivity().supportFragmentManager, "")
        val repository = EditShopRepoProvider.provideEditShopWithoutImageRepository()
        BaseActivity.compositeDisposable.add(
            repository.targetType(Pref.user_id.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    val resp = result as TargetTypeResponse
                    if(resp.status == NetworkConstant.SUCCESS){
                        targetTypeL = resp.target_type_list
                        callTargetLevel()
                    }else{
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                        DialogLoading.dismiss()
                    }
                }, { error ->
                    error.printStackTrace()
                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    DialogLoading.dismiss()
                })
        )
    }

    private fun callTargetLevel(){
        val repository = EditShopRepoProvider.provideEditShopWithoutImageRepository()
        BaseActivity.compositeDisposable.add(
            repository.targetLevel(Pref.user_id.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    val resp = result as TargetLevelResponse
                    if(resp.status == NetworkConstant.SUCCESS){
                        targetLevelL = resp.target_level_list
                        DialogLoading.dismiss()
                    }else{
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                        DialogLoading.dismiss()
                    }
                }, { error ->
                    error.printStackTrace()
                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    DialogLoading.dismiss()
                })
        )
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            llTargetType.id ->{
                if(targetTypeL.size>0){
                    var genericL:ArrayList<CustomData> = ArrayList()
                    for(i in 0..targetTypeL.size-1){
                        genericL.add(CustomData(targetTypeL[i].id.toString(),targetTypeL[i].name))
                    }

                    GenericDialog.newInstance("Target Type",genericL as ArrayList<CustomData>){
                        tvTargetType.text = it.name
                        selectedTargetType = it.id.toString()
                    }.show((mContext as DashboardActivity).supportFragmentManager, "")
                }else{
                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                }
            }
            llTargetLevel.id ->{
                if(targetLevelL.size>0){
                    var genericL:ArrayList<CustomData> = ArrayList()
                    for(i in 0..targetLevelL.size-1){
                        genericL.add(CustomData(targetLevelL[i].id.toString(),targetLevelL[i].name))
                    }

                    GenericDialog.newInstance("Target Level",genericL as ArrayList<CustomData>){
                        tvTargetLevel.text = it.name
                        selectedTargetLevel = it.id.toString()
                    }.show((mContext as DashboardActivity).supportFragmentManager, "")
                }else{
                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                }
            }
        }
    }

    private fun callDtlsApi(){
        if(selectedTargetType.equals("")){
            (mContext as DashboardActivity).showSnackMessage("Please select Target Type")
        }else if(selectedTargetLevel.equals("")){
            (mContext as DashboardActivity).showSnackMessage("Please select Target Level")
        }else if(selectedTimeFrame.equals("")){
            (mContext as DashboardActivity).showSnackMessage("Please select Time Frame")
        }else{

        }
    }

}