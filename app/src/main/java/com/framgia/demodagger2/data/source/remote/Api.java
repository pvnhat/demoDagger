package com.framgia.demodagger2.data.source.remote;

import com.framgia.demodagger2.data.model.Student;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    /**
     * // gui gi do len khong phai dang string
     */
    @Multipart
    @POST("uploadImage.php")
    Observable<String> upLoadPhoto(
            @Part MultipartBody.Part photo); // nhan du lieu tra ve tu server / kieu du lieu gui di

    /**
     * post data dang String, FormUrlEncode su dung chung voi Field va POST de thay doi du lieu
     * tren api
     */
    @FormUrlEncoded
    @POST("insertStudent.php")
    Observable<String> insertStudent(@Field("Username") String username,
            @Field("Password") String password, @Field("Avatar") String avatar);

    @FormUrlEncoded
    @POST("checkLogin.php")// GET DATA WITH PARAMENTER
    Observable<List<Student>> getLoginData(@Field("Username") String username,
            @Field("Password") String password);

    @GET("getAllStudent.php")
    Observable<List<Student>> getAllStudent(@Query("limit") int limitNumber);
}
