package com.nuppu.LocalSearch;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.nuppu.LocalSearch.Structure.Advert;
import com.nuppu.LocalSearch.Structure.Laundry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        imageView = (ImageView) findViewById(R.id.imageView);

        DownAdvert advert = new DownAdvert();
        advert.execute();
    }

    public void openElectricianActivity(View view) {
        Intent intent = new Intent(this, ElectricianActivity.class);
        startActivity(intent);
    }

    public void openLAUNDRY(View view) {
        Intent intent = new Intent(this, Laundry.class);
        startActivity(intent);
   }

    public void openFlowers(View view) {
        Intent intent = new Intent(this, FlowersActivity.class);
        startActivity(intent);
    }

    public void openTraining(View view) {
        Intent intent = new Intent(this, Flowers.class);
        startActivity(intent);
    }

       public  void openBanner(View view){

           Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
           startActivity(intent);
       }




    class DownAdvert extends AsyncTask<String, Intent, ArrayList<Advert>> {

        ArrayList<Advert> arrayList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
        }

        @Override
        protected ArrayList<Advert> doInBackground(String... params) {
            try {
                String data = DownloadJsonContent.downloadContent(Fields.URL_ADVERTISEMENT);
                JSONObject object = new JSONObject(data);
                JSONArray array = object.getJSONArray("advertisement");
                for (int i = 0; i < array.length(); i++) {
                    Advert advert = new Advert();
                    JSONObject jsonObject = array.getJSONObject(i);
                    String url = jsonObject.getString("imageurl");
                    String redirectUrl = jsonObject.getString("redirecturl");
                    advert.setImageURL(url);
                    advert.setRedirectURL(redirectUrl);
                    arrayList.add(advert);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return arrayList;
        }

        @Override
        protected void onPostExecute(final ArrayList<Advert> adverts) {
            super.onPostExecute(adverts);


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (adverts.size() > 0) {
                        int l = adverts.size();
                        int incr = 0;
                        while (true) {
                            if (incr == l - 1) {
                                incr = 0;
                            } else {
                                incr = incr + 1;
                            }
                            final int finalIncr = incr;
                            runOnUiThread(new Runnable() {
                                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                                @Override
                                public void run() {

                                    if (!HomeActivity.this.isDestroyed()) {


                                        Glide
                                                .with(HomeActivity.this)
                                                .load(adverts.get(finalIncr).getImageURL())
                                                .override(1000,300)
                                                .into(imageView);
                                    }
                                }
                            });
                            Log.e(Fields.Flowers, adverts.get(0).getImageURL());
                            synchronized (this) {
                                try {
                                    this.wait(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
            thread.start();


        }
    }
}
