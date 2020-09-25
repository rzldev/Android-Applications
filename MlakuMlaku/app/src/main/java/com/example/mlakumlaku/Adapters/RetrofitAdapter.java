package com.example.mlakumlaku.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mlakumlaku.Activities.LoginActivity;
import com.example.mlakumlaku.Activities.MainActivity;
import com.example.mlakumlaku.Databases.APIPackages;
import com.example.mlakumlaku.Requests.LoginRequest;
import com.example.mlakumlaku.Requests.UserRequest;
import com.example.mlakumlaku.Responses.LoginResponse;
import com.example.mlakumlaku.Responses.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110/android/uas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIPackages apiPackages = retrofit.create(APIPackages.class);

    List<UserRequest> userRequestList;

    String url = null;
    ImageLoader imageLoader;
    NetworkImageView previewImage;

    public RetrofitAdapter(Context context) {
        this.context = context;
    }

    public void register(String username, String email, String password, String birthDate, String address, String facebook, String instagram, int events,
                         String profileTitle, String profileImage, String bgTitle, String bgImage) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<RegisterResponse> registerResponseCall = apiPackages.REGISTER_RESPONSE_CALL(
                username, email, password, birthDate, address, facebook, instagram, events, profileTitle, profileImage, bgTitle, bgImage);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    } else {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void login(final String email, final String password) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<LoginResponse> loginResponseCall = apiPackages.LOGIN_RESPONSE_CALL(email, password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    int loginStatus = response.body().getSuccess();
                    if (loginStatus == 1) {
                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();

                        List<LoginRequest> user = response.body().getUser();
                        LoginRequest u = user.get(0);

                        DaoAdapter da = new DaoAdapter(context);
                        da.saveUser(Integer.parseInt(u.getId()), email, password);

                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();

                    } else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void FetchImage(String photoUrl) {
//        {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST,photoUrl,
//                    new com.android.volley.Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//                            try {
//                                JSONObject res = new JSONObject(response);
//                                JSONArray thread = res.getJSONArray("image");
//                                for (int i = 0; i < thread.length(); i++) {
//                                    JSONObject obj = thread.getJSONObject(i);
//                                    url  = obj.getString("photo");
//
//
//                                }
//
//                                imageLoader.get(url, ImageLoader.getImageListener(previewImage
//                                        ,0,android.R.drawable
//                                                .ic_dialog_alert));
//                                previewImage.setImageUrl(url, imageLoader);
//
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                    },
//                    new com.android.volley.Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    return params;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
//
//            //Adding request to the queue
//            requestQueue.add(stringRequest);
//        }
//
//    }

}
