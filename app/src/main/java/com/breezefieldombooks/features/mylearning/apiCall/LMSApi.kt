package com.breezefieldombooks.features.mylearning.apiCall

import com.breezefieldombooks.app.NetworkConstant
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.addshop.model.AddQuestionSubmitRequestData
import com.breezefieldombooks.features.leaderboard.api.LeaderboardOverAllData
import com.breezefieldombooks.features.leaderboard.api.LeaderboardOwnData
import com.breezefieldombooks.features.login.api.opportunity.OpportunityListApi
import com.breezefieldombooks.features.login.model.opportunitymodel.OpportunityStatusListResponseModel
import com.breezefieldombooks.features.mylearning.BookmarkFetchResponse
import com.breezefieldombooks.features.mylearning.BookmarkResponse
import com.breezefieldombooks.features.mylearning.CONTENT_WISE_QA_SAVE
import com.breezefieldombooks.features.mylearning.ContentCountSave_Data
import com.breezefieldombooks.features.mylearning.LMSLeaderboardOverAllData
import com.breezefieldombooks.features.mylearning.LMSLeaderboardOwnData
import com.breezefieldombooks.features.mylearning.LMS_CONTENT_INFO
import com.breezefieldombooks.features.mylearning.MyCommentListResponse
import com.breezefieldombooks.features.mylearning.MyLarningListResponse
import com.breezefieldombooks.features.mylearning.SectionsPointsList
import com.breezefieldombooks.features.mylearning.TopicContentWiseAnswerListsFetchResponse
import com.breezefieldombooks.features.mylearning.TopicListResponse
import com.breezefieldombooks.features.mylearning.VideoTopicWiseResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LMSApi {

    @FormUrlEncoded
    @POST("LMSInfoDetails/TopicLists")
    fun getTopics(@Field("user_id") user_id: String): Observable<TopicListResponse>

    @FormUrlEncoded
    @POST("LMSInfoDetails/TopicWiseLists")
    fun getTopicswiseVideoApi(@Field("user_id") user_id: String,@Field("topic_id") topic_id: String): Observable<VideoTopicWiseResponse>

    @POST("LMSInfoDetails/TopicContentDetailsSave")
    fun saveContentInfoApi(@Body lms_content_info: LMS_CONTENT_INFO?): Observable<BaseResponse>

    @FormUrlEncoded
    @POST("LMSInfoDetails/LearningContentLists")
    fun getMyLearningContentList(@Field("user_id") user_id: String): Observable<MyLarningListResponse>

    @FormUrlEncoded
    @POST("LMSInfoDetails/CommentLists")
    fun getCommentInfo(@Field("topic_id") topic_id: String , @Field("content_id") content_id: String): Observable<MyCommentListResponse>

    @POST("LMSInfoDetails/TopicContentWiseQASave")
    fun saveContentWiseQAApi(@Body mCONTENT_WISE_QA_SAVE: CONTENT_WISE_QA_SAVE): Observable<BaseResponse>

    @POST("LMSInfoDetails/ContentCountSave")
    fun saveContentCount(@Body mContentCountSave_Data: ContentCountSave_Data): Observable<BaseResponse>

    @FormUrlEncoded
    @POST("LMSInfoDetails/LMSLeaderboardOwnList")
    fun ownDatalist(@Field("user_id") user_id: String,@Field("branchwise") branchwise: String,@Field("flag") flag: String): Observable<LMSLeaderboardOwnData>

    @FormUrlEncoded
    @POST("LMSInfoDetails/LMSLeaderboardOverallList")
    fun overAllDatalist(@Field("user_id") user_id: String,@Field("branchwise") branchwise: String,@Field("flag") flag: String): Observable<LMSLeaderboardOverAllData>

    @FormUrlEncoded
    @POST("LMSInfoDetails/LMSSectionsPointsList")
    fun overAllDatalist(@Field("session_token") session_token: String): Observable<SectionsPointsList>

    @POST("LMSInfoDetails/LMSSaveBookMark")
    fun bookmarkApiCallApi(@Body obj: BookmarkResponse): Observable<BaseResponse>

    @FormUrlEncoded
    @POST("LMSInfoDetails/LMSFetchBookMark")
    fun getBookmarkedApiCallApi(@Field("user_id") user_id: String): Observable<BookmarkFetchResponse>

    @FormUrlEncoded
    @POST("LMSInfoDetails/TopicContentWiseAnswerLists")
    fun getTopicContentWiseAnswerLists(@Field("user_id") user_id: String,@Field("topic_id") topic_id: String,@Field("content_id") content_id: String): Observable<TopicContentWiseAnswerListsFetchResponse>

    companion object Factory {
        fun create(): LMSApi {
            val retrofit = Retrofit.Builder()
                .client(NetworkConstant.setTimeOut())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConstant.BASE_URL)
                .build()

            return retrofit.create(LMSApi::class.java)
        }
    }
}