package com.kadazi.poliklinikapps.Api;


import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelAntrian;
import com.kadazi.poliklinikapps.Model.ResponseModelDetailJadwal;
import com.kadazi.poliklinikapps.Model.ResponseModelDetailPemeriksaan;
import com.kadazi.poliklinikapps.Model.ResponseModelMediaPembayaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaranBiaya;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaranResep;
import com.kadazi.poliklinikapps.Model.ResponseModelPoli;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaranBaru;
import com.kadazi.poliklinikapps.Model.ResponseModelResep;
import com.kadazi.poliklinikapps.Model.ResponseModelResepDetails;
import com.kadazi.poliklinikapps.Model.ResponseModelRiwayat;

import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface APIRequestData {
    @FormUrlEncoded
    @POST("auth")
    Call<ResponseModel> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("resep/{id}")
    Call<ResponseModelResep> listResep(@Path("id")String id);
    @GET("riwayat_pemeriksaan/{id}")
    Call<ResponseModelRiwayat> listRiwayat(@Path("id") int id);
    @GET("detail_pemeriksaan/{id}")
    Call<ResponseModelDetailPemeriksaan> Detail(@Path("id") int id);
    @GET("details_resep/{id}")
    Call<ResponseModelResepDetails> DetailS_Resep(@Path("id") int id);
    @GET("jadwal_dokter/{id}")
    Call<ResponseModelDetailJadwal> detail_jadwal(@Path("id") String id);
    @GET("pembayaran/{id}")
    Call<ResponseModelPembayaran> pembayaran(@Path("id") String id);
    @GET("antrian_daftar")
    Call<ResponseModelAntrian> ListAntrian();
    @GET("poli")
    Call<ResponseModelPoli> poli();
    @GET("pembayaran/obat/{id}")
    Call<ResponseModelPembayaranResep> pembayaran_resep(@Path("id") String id);
    @GET("pembayaran/biaya/{id}")
    Call<ResponseModelPembayaranBiaya> pembayaran_biaya(@Path("id") String id);
    @GET("media_pembayaran")
    Call<ResponseModelMediaPembayaran> media_pembayaran();
    @GET("daftar/{id}")
    Call<ResponseModelPendaftaran> ListDaftar(@Path("id") int id);
    @GET("daftar_baru")
    Call<ResponseModelPendaftaranBaru> ListDaftarBaru();

    @DELETE("hapus_daftar/{id}")
    Call<ResponseModel> HapusDaftar(@Path("id") int id);

    @FormUrlEncoded
    @POST("pendaftaran")
    Call<ResponseModel> Pendaftaran(
            @Field("jadwal_dokter") String id_jadwal,
            @Field("no_pasien") int no_pasien
    );

    @FormUrlEncoded
    @POST("pasien")
    Call<ResponseModel> Daftar(
            @Field("email") String email,
            @Field("password") String password,
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jk") String jk,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp
    );
    @Multipart
    @POST("pembayaran")
    Call<ResponseModel> upload(
            @Part("id") RequestBody id,
            @Part MultipartBody.Part image
    );


}
