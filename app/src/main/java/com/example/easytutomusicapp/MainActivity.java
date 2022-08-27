package com.example.easytutomusicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easytutomusicapp.fragment.PurchaseFragment;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noMusicTextView;
    ImageView btnPurchaser;
    ArrayList<AudioModel> songsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        noMusicTextView = findViewById(R.id.no_songs_text);
        btnPurchaser = findViewById(R.id.btnPurchase);
        if(checkPermission() == false){
            requestPermission();
            return;
        }
        AudioModel dataListMusicItem1 = new AudioModel("R.raw.one","Chris Later & Dany Yeager - There's Nobody Else","02:41");
        AudioModel dataListMusicItem2 = new AudioModel("R.raw.two","Andromedik & Used - Take Me","03:13");
        AudioModel dataListMusicItem3 = new AudioModel("R.raw.three","Egzod, Maestro Chives & Alaina Cross - No Rival","03:18");
        AudioModel dataListMusicItem4 = new AudioModel("R.raw.forr","NIVIRO - The Edge (feat. Harley Bird)","03:22");
        songsList.add(dataListMusicItem1);
        songsList.add(dataListMusicItem2);
        songsList.add(dataListMusicItem3);
        songsList.add(dataListMusicItem4);

        if(songsList.size()==0){
            noMusicTextView.setVisibility(View.VISIBLE);
        }else{
            //recyclerview
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdapter(songsList,getApplicationContext()));
        }

       btnPurchaser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getSupportFragmentManager().beginTransaction()
                       .setReorderingAllowed(true)
                       .add(R.id.fragment_container_view, PurchaseFragment.class, null)
                       .addToBackStack("PurchaseFragment")
                       .commit();
           }
       });

    }

    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recyclerView!=null){
            recyclerView.setAdapter(new MusicListAdapter(songsList,getApplicationContext()));
        }
    }
}