package com.example.amal.esa.network;




import com.example.amal.esa.model.LoginRequest;
import com.example.amal.esa.model.LoginResponse;
import com.example.amal.esa.model.News;
import com.example.amal.esa.model.RegiserResponse;
import com.example.amal.esa.model.RegisterRequest;
import com.example.amal.esa.ui.news.Movie;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


public interface ApiInterface {

    /* *//* @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*//*

     *//*  @POST("sign_in")
    FooResponse postJson(@Body FooRequest body);*//*

    @POST("sign_in")
    Call<ResponseLogin> login(@Body RequestLogin loginData);


    @Multipart
    @POST("pictures")
    Call<ImageResponse> postImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);


    @POST("reports")
    Call<ResponseBody> sendReport(@Body ReportRequest sentReport);


  *//*  @GET("reports/1")
    Call<ResponseBody> doGetListResources();*//*

    @POST("live_reports")
    Call<FetchReport> getReport(@Body RequestGetReport requestGetReport);


    @POST("load_data")
        // for spinner
    Call<Spinnerdata> getData(@Body ReportRequest requestGetReport);

    @GET("checklists")
        //for checklist
    Call<Insception> getCheckList(@Query("user_token") String user_token);

    @Multipart
    @POST("questions")
    Call<CheckListDataForm> SendCheckListData(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);


    @Multipart
    @POST("reports")
    Call<CheckListDataForm> CreateReport(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);


    @POST("profile")
        // for spinner
    Call<Profile> getProfile(@Body ProfileRequest requestGetReport);

    @PUT("reports/{id}")
    Call<ResponseBody> updateRecord(@Path("id") int id, @Body UpdateRecord updateRecord);*/

    /* @Headers("api-key: NitintestKeySunilPankaj")
     @POST("user/login")
     Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);
 */
    //@Headers("api-key: Y2xpeEFwaVBlbm55RHJvchiuhv76")
    @POST("api/v1/accounts/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("api/v1/accounts/register/")
    Call<RegiserResponse> register(@Body RegisterRequest loginRequest);

    @GET("/api/v1/news")
    Call<Movie> getNews(
            @HeaderMap Map<String, String> headers
    );

/*
    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/signUp")
    Call<SignupResponse> signup(@Body SignupRequest requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/verifyOTP")
    Call<LoginResponse> verify_otp(@Body VerifyRequest requestGetReport);

    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/resendOTP")
    Call<ResponseBody> resend_otp(@Body VerifyRequest requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/feedback")
    Call<ResponseBody> appfeedback(@Body VerifyRequest requestGetReport);


   *//* @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/resendOTP")
    Call<VerifyResponse> resend_otp(@Body VerifyRequest requestGetReport);*//*


    // http://auction.nupayonline.com/lda/api/user/verifyOTP

    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/forgotPasswordOtp")
    Call<ResponseBody> forgot_verify_otp(@Body VerifyRequest requestGetReport);

    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/forgotPassword")
    Call<ResponseBody> forgot_password(@Body VerifyRequest requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("user/userFeedback")
    Call<ResponseBody> userFeedback(@Body Feedback requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Lottery/schemeList")
    Call<SchemeList> getSchemesList(@Body Feedback requestGetReport);

    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Lottery/lotteryWinnerList")
    Call<LotteryResult> getLottryList(@Body Feedback requestGetReport);

    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Property/getProperty")
    Call<SearchPropertyResponse> getPropertyDetail(@Body SearchPropertyRequest requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Property/getProperty")
    Call<GetMandateResponse> getNewRegistrationDetail(GetMandateRequest newRegistrationRequest);


   *//* @Headers("api-key: NitintestKeySunilPankaj")
    @GET("Lottery/schemeList")
    Call<SchemeList> getSchemesList();
*//*

    @Headers("api-key: NitintestKeySunilPankaj")
    @GET("Lottery/lotteryWinnerList")
    Call<LotteryResult> getLottryList();


    @GET("movie/top_rated")
    Call<GetMandateResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int pageIndex);


    @Headers("api-key: NitintestKeySunilPankaj")
    @GET("Lottery/residentialList")
    Call<GetMandateResponse> getresidentialList();


    @Headers("api-key: NitintestKeySunilPankaj")
    @GET("Lottery/getActiveList")
    Call<ActiveFlag> getActiveList();

    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Property/getDeposite")
    Call<PaymentHistory> getPaymentHistory(@Body Feedback requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Property/getDueDetails")
    Call<DueDetails> getDueDetails(@Body Feedback requestGetReport);


    @Headers("api-key: NitintestKeySunilPankaj")
    @GET("Lottery/residentialListCount")
    Call<ResponseBody> getCount();


    @Headers("api-key: NitintestKeySunilPankaj")
    @GET("Property/getNewsList")
    Call<News> getNewsList();*/

   /* @Headers("api-key: NitintestKeySunilPankaj")
    @POST("Property/getProperty")
    Call<GetMandateResponse> getNewRegistrationDetail(GetMandateRequest newRegistrationRequest);*/

   /* @Headers("api-key: Y2xpeEFwaVBlbm55RHJvchiuhv76")
    @POST("getMandateList")
    Call<GetMandateResponse> getresidentialList(@Body GetMandateRequest getMandateRequest);
*/

    @Headers("api-key: Y2xpeEFwaVBlbm55RHJvchiuhv76")
    @Multipart
    @POST("uploadMandate")
    Call<ResponseBody> postImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);

    /*@Headers("api-key: Y2xpeEFwaVBlbm55RHJvchiuhv76")
    @Multipart
    @POST("uploadMandateTest")
    Call<ResponseBody> postImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);

*/
    /*@Headers("api-key: Y2xpeEFwaVBlbm55RHJvchiuhv76")
    @POST("getMandateByDate")
    Call<Nach> getNach(@Body GetMandateRequest getMandateRequest);*/
}
