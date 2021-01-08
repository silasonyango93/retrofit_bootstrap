package com.maryadhiambo.apiconnect;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Repository {
    @FormUrlEncoded
    @POST("insert_users")
    Call<SuccessfulInsertionResponse> insertUsers(@Field("firstName") String firstName,
                                                  @Field("middleName") String middleName,
                                                  @Field("surname") String surname,
                                                  @Field("email") String email);
}
