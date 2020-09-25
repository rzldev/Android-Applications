package com.example.benakno.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.benakno.R;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cvHotel, cvOffice, cvHouse;
    TextView permassalahan, merkAC, jumlahAC, jumlahPK;
    Button tambahAC, kurangAC, tambahPK, kurangPK, btnOrder;

    String jenisBangunan, typePopUp, tipe;
    String getMasalah, getMerk;
    int totalAC;
    double totalPK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent gI = getIntent();
        tipe = gI.getStringExtra("tipe");

        initializing();
        setDefaultCardElevation();
        myClick();
    }

    private void setDefaultCardElevation() {
        cvHotel.setCardElevation(4);
        cvOffice.setCardElevation(4);
        cvHouse.setCardElevation(4);
    }

    private void myClick() {
        cvHotel.setOnClickListener(this);
        cvOffice.setOnClickListener(this);
        cvHouse.setOnClickListener(this);

        permassalahan.setOnClickListener(this);
        merkAC.setOnClickListener(this);

        tambahAC.setOnClickListener(this);
        kurangAC.setOnClickListener(this);
        tambahPK.setOnClickListener(this);
        kurangPK.setOnClickListener(this);

        btnOrder.setOnClickListener(this);
    }

    private void initializing() {
        cvHotel = findViewById(R.id.cvHotel);
        cvOffice = findViewById(R.id.cvOffice);
        cvHouse = findViewById(R.id.cvHouse);
        permassalahan = findViewById(R.id.inputMasalah);
        merkAC = findViewById(R.id.inputMerkAC);
        jumlahAC = findViewById(R.id.jumlahAC);
        jumlahPK = findViewById(R.id.jumlahPK);
        tambahAC = findViewById(R.id.btnTambah);
        kurangAC = findViewById(R.id.btnKurang);
        tambahPK = findViewById(R.id.btnTambahPK);
        kurangPK = findViewById(R.id.btnKurangPK);
        btnOrder = findViewById(R.id.btnOrder);

        if (tipe.equals("ledeng")) {
            merkAC.setHint("Merk Ledeng");
            jumlahAC.setHint("Jumlah Ledeng");
            tambahPK.setVisibility(View.GONE);
            jumlahPK.setVisibility(View.GONE);
            kurangPK.setVisibility(View.GONE);

        }

        totalAC = 0;
        totalPK = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvHotel :
                cvHotel.setCardElevation(16);
                cvOffice.setCardElevation(4);
                cvHouse.setCardElevation(4);

                jenisBangunan = "Hotel";
                break;

            case R.id.cvOffice :
                cvHotel.setCardElevation(4);
                cvOffice.setCardElevation(16);
                cvHouse.setCardElevation(4);

                jenisBangunan = "Kantor";
                break;

            case R.id.cvHouse :
                cvHotel.setCardElevation(4);
                cvOffice.setCardElevation(4);
                cvHouse.setCardElevation(16);

                jenisBangunan = "Rumah";
                break;

            case R.id.inputMasalah :
                typePopUp = "masalah";
                chooseMasalah();
                break;

            case R.id.inputMerkAC :
                typePopUp = "merk";
                chooseMerk();
                break;

            case R.id.btnTambah :
                totalAC += 1;
                if (totalAC < 0) {
                    totalAC = 0;
                }

                if (tipe.equals("ac")) {
                    jumlahAC.setText(String.valueOf(totalAC) + " AC");
                } else {
                    jumlahAC.setText(String.valueOf(totalAC) + " Ledeng");
                }

                break;

            case R.id.btnKurang :
                totalAC -= 1;
                if (totalAC < 0) {
                    totalAC = 0;
                }

                if (tipe.equals("ac")) {
                    jumlahAC.setText(String.valueOf(totalAC) + " AC");
                } else {
                    jumlahAC.setText(String.valueOf(totalAC) + " Ledeng");
                }

                break;

            case R.id.btnTambahPK :
                totalPK += 0.5;
                if (totalPK < 0) {
                    totalPK = 0;
                }
                jumlahPK.setText(String.valueOf(totalPK) + " PK");

                break;

            case R.id.btnKurangPK :
                totalPK -= 0.5;
                if (totalPK < 0) {
                    totalPK = 0;
                }
                jumlahPK.setText(String.valueOf(totalPK) + " PK");

                break;

            case R.id.btnOrder :
                Intent intent = new Intent(OrderActivity.this, FakeMapsActivity.class);
                intent.putExtra("tipe", tipe);
                intent.putExtra("merk", merkAC.getText().toString());
                intent.putExtra("quantity", String.valueOf(totalAC));
                intent.putExtra("desc", String.valueOf(totalPK));
                intent.putExtra("building", jenisBangunan);
                startActivity(intent);

                break;
        }
    }

    private void chooseMerk() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_merk_ac, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        popupWindow.setWidth(width - 100);
        popupWindow.setHeight(height - 200);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(new RelativeLayout(this), Gravity.CENTER, 0, 0);

        exit(popupView, popupWindow);

        final TextView merk1, merk2, merk3, merk4, merk5, merkLain;

        merk1 = popupView.findViewById(R.id.merk1);
        merk2 = popupView.findViewById(R.id.merk2);
        merk3 = popupView.findViewById(R.id.merk3);
        merk4 = popupView.findViewById(R.id.merk4);
        merk5 = popupView.findViewById(R.id.merk5);
        merkLain = popupView.findViewById(R.id.merkLain);

        if (tipe.equals("ac")) {
            merk1.setText("Panasonic");
            merk2.setText("Sharp");
            merk3.setText("LG");
            merk4.setText("Samsung");
            merk5.setText("Changhong");

        } else {
            merk1.setText("Sanyo");
            merk2.setText("Hitachi");
            merk3.setText("Sasuke");
            merk4.setText("Naruto");
            merk5.setText("Simizhu");
        }

        final PopupWindow popupWindow1 = popupWindow;

        merk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMerk = merk1.getText().toString();
                popupWindow1.dismiss();
                merkAC.setText(getMerk);
            }
        });

        merk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMerk = merk2.getText().toString();
                popupWindow1.dismiss();
                merkAC.setText(getMerk);
            }
        });

        merk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMerk = merk3.getText().toString();
                popupWindow1.dismiss();
                merkAC.setText(getMerk);
            }
        });

        merk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMerk = merk4.getText().toString();
                popupWindow1.dismiss();
                merkAC.setText(getMerk);
            }
        });

        merk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMerk = merk5.getText().toString();
                popupWindow1.dismiss();
                merkAC.setText(getMerk);
            }
        });

        merkLain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMerk = merkLain.getText().toString();
                popupWindow1.dismiss();
                merkAC.setText(getMerk);
            }
        });


    }

    private void chooseMasalah() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_masalah, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        popupWindow.setWidth(width - 100);
        popupWindow.setHeight(height - 200);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(new RelativeLayout(this), Gravity.CENTER, 0, 0);

        exit(popupView, popupWindow);

        final TextView masalah1, masalah2, masalah3, masalah4, masalah5, masalahLain;

        masalah1 = popupView.findViewById(R.id.masalah1);
        masalah2 = popupView.findViewById(R.id.masalah2);
        masalah3 = popupView.findViewById(R.id.masalah3);
        masalah4 = popupView.findViewById(R.id.masalah4);
        masalah5 = popupView.findViewById(R.id.masalah5);
        masalahLain = popupView.findViewById(R.id.masalahLain);

        if (tipe.equals("ac")) {
            masalah1.setText("AC Bocor");
            masalah2.setText("AC Bau Tidak Sedap");
            masalah3.setText("AC Berisik");
            masalah4.setText("Ac Tidak Dingin");
            masalah5.setText("AC Tidak Nyala");

        } else if (tipe.equals("ledeng")) {
            masalah1.setText("Ledeng Macet");
            masalah2.setText("Ledeng Mengeluarkan Air Kotor");
            masalah3.setText("Ledeng Bocor");
            masalah4.setText("Ledeng Berisik");
            masalah5.setText("Ledeng Mati");

        }

        final PopupWindow popupWindow1 = popupWindow;

        masalah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMasalah = masalah1.getText().toString();
                popupWindow1.dismiss();
                permassalahan.setText(getMasalah);
            }
        });

        masalah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMasalah = masalah2.getText().toString();
                popupWindow1.dismiss();
                permassalahan.setText(getMasalah);
            }
        });

        masalah3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMasalah = masalah3.getText().toString();
                popupWindow1.dismiss();
                permassalahan.setText(getMasalah);
            }
        });

        masalah4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMasalah = masalah4.getText().toString();
                popupWindow1.dismiss();
                permassalahan.setText(getMasalah);
            }
        });

        masalah5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMasalah = masalah5.getText().toString();
                popupWindow1.dismiss();
                permassalahan.setText(getMasalah);
            }
        });

        masalahLain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMasalah = masalahLain.getText().toString();
                popupWindow1.dismiss();
                permassalahan.setText(getMasalah);
            }
        });


    }

    private void exit(View popupView, final PopupWindow popupWindow) {
        ConstraintLayout btnExit = popupView.findViewById(R.id.close);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
}
