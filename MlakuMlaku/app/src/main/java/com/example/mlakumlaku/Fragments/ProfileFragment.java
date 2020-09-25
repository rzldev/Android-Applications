package com.example.mlakumlaku.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mlakumlaku.Activities.WebActivity;
import com.example.mlakumlaku.Classes.User;
import com.example.mlakumlaku.Databases.APIPackages;
import com.example.mlakumlaku.Adapters.DaoAdapter;
import com.example.mlakumlaku.R;
import com.example.mlakumlaku.Requests.UserRequest;
import com.example.mlakumlaku.Responses.UserResponse;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    ImageView profileBg;
    CircleImageView profilePp;
    TextView username, email, birthDate, address, events, facebook, instagram;
    Button logout;
    WebView myWeb;

    List<User> user;

    DaoAdapter daoAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.110/android/uas/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIPackages apiPackages = retrofit.create(APIPackages.class);

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        initialize(v);
        myClick();
        loadUser();

        return v;
    }

    private void loadUser() {
        user = daoAdapter.showUser();
        User u = user.get(0);

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Call<UserResponse> userResponseCall = apiPackages.USER_RESPONSE_CALL(u.getId());
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    List<UserRequest> userRequestList = response.body().getUser();
                    UserRequest ur = userRequestList.get(0);
                    username.setText(ur.getUsername());
                    email.setText(ur.getEmail());
                    birthDate.setText(Html.fromHtml("Berulang tahun pada <b>" + ur.getBirthDate() + "</b>"));
                    address.setText(Html.fromHtml("Tinggal di <b>" + ur.getAddress() + "</b>"));
                    events.setText(Html.fromHtml("Telah mengikuti <b>" + String.valueOf(ur.getEvents()) + " Events</b>"));
                    facebook.setText(ur.getFacebook());
                    instagram.setText(ur.getInstagram());

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void myClick() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoAdapter.deleteUser(1);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", facebook.getText().toString());
                startActivity(intent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", instagram.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initialize(View v) {
        profileBg = v.findViewById(R.id.profileBg);
        profilePp = v.findViewById(R.id.profileImg);
        username = v.findViewById(R.id.profileName);
        email = v.findViewById(R.id.profileEmail);
        birthDate = v.findViewById(R.id.date);
        address = v.findViewById(R.id.address);
        events = v.findViewById(R.id.walk);
        facebook = v.findViewById(R.id.facebookLink);
        instagram = v.findViewById(R.id.instagramLink);
        logout = v.findViewById(R.id.btnLogOut);
        myWeb = v.findViewById(R.id.myWeb);

        daoAdapter = new DaoAdapter(getActivity().getApplicationContext());
        user = new ArrayList<>();
    }

}
