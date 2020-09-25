package com.example.retrofitapplication.Classes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.retrofitapplication.Adapters.MainAdapter;
import com.example.retrofitapplication.Classes.InsertResponse;
import com.example.retrofitapplication.Classes.MainRequest;
import com.example.retrofitapplication.Classes.MainResponse;
import com.example.retrofitapplication.Databases.APIPakcages;
import com.example.retrofitapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Popup extends Activity {

    private static final int PICK_IMAGE_REQUEST = 1000;
    private Context context;
    private APIPakcages apiPakcages;
    List<MainRequest> mainRequestList = new ArrayList<>();
    Uri gambarUri;
    Bitmap bitmap;

    EditText nama, nim;
    Button insert;

    public Popup(Context context, Retrofit retrofit) {
        this.context = context;
        apiPakcages = retrofit.create(APIPakcages.class);
    }

    public void showPopup(final MainAdapter adapter) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.item_insert, null);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        int width = size.x;

        popupWindow.setWidth(width - 100);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(new RelativeLayout(context), Gravity.CENTER, 0, 0);

        View container = (View) popupWindow.getContentView().getRootView();
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) container.getLayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.3f;
        windowManager.updateViewLayout(container, layoutParams);

        nama = popupView.findViewById(R.id.nama);
        nim = popupView.findViewById(R.id.nim);
        insert = popupView.findViewById(R.id.insertBtn);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = nama.getText().toString();
                String getNim = nim.getText().toString();

                if (getNama.isEmpty()) {
                    nama.setError("Kosong Bro");
                    nama.requestFocus();
                    return;
                }
                if (getNim.isEmpty()) {
                    nim.setError("Kosong Bro");
                    nim.requestFocus();
                    return;
                }

                insertData(apiPakcages, getNama, getNim);
            }

            private void insertData(final APIPakcages api, String getNama, String getNim) {
                final ProgressDialog dialog = new ProgressDialog(context);
                dialog.setCancelable(false);
                dialog.setMessage("Loading...");
                dialog.show();

                Call<InsertResponse> insertResponseCall = api.INSERT_RESPONSE_CALL(getNama, getNim);
                insertResponseCall.enqueue(new Callback<InsertResponse>() {
                    @Override
                    public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                        dialog.dismiss();
                        int success = response.body().getSuccess();
                        if (success == 1) {
                            Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();

                            Call<MainResponse> mainResponseCall = api.MAIN_RESPONSE_CALL();
                            mainResponseCall.enqueue(new Callback<MainResponse>() {
                                @Override
                                public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                                    mainRequestList = response.body().getMahasiswa();

                                    adapter.clear();
                                    adapter.addNewData(mainRequestList);

                                }

                                @Override
                                public void onFailure(Call<MainResponse> call, Throwable t) {

                                }
                            });

                            popupWindow.dismiss();

                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<InsertResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

//    private void chooseImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_PICK);
//        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
//            gambarUri = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), gambarUri);
//                gambar.setImageBitmap(bitmap);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//        }
//    }

}
