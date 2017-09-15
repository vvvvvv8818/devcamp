package yapp.dev_diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YoungJung on 2017-08-20.
 */
//
//public class Main_Detail extends AppCompatActivity {
//    static ArrayList<String> pic_path = new ArrayList<>();
//    static ArrayList<String> ok_path = new ArrayList<>();
//    TextView tv_camera,tv_camera2,tv_camera3,tv_camera4,tv_camera5;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        long now = System.currentTimeMillis();
//        Date date = new Date(now);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String getTime = sdf.format(date);
//        ImageView image = (ImageView) findViewById(R.id.test_img);
//        ImageView image2 = (ImageView) findViewById(R.id.test_img2);
//        ImageView image3 = (ImageView) findViewById(R.id.test_img3);
//        ImageView image4 = (ImageView) findViewById(R.id.test_img4);
//        ImageView image5 = (ImageView) findViewById(R.id.test_img5);
//
//        Button btn = (Button) findViewById(R.id.btn_finish);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Main_Detail.this, ResultActivity.class);
//                startActivity(i);
//            }
//        });
//        tv_camera = (TextView) findViewById(R.id.tv_camera);
//        tv_camera2 = (TextView) findViewById(R.id.tv_camera2);
//        tv_camera3 = (TextView) findViewById(R.id.tv_camera3);
//        tv_camera4 = (TextView) findViewById(R.id.tv_camera4);
//        tv_camera5 = (TextView) findViewById(R.id.tv_camera5);
//        getImageNameToUri();
//        //이미지 데이터를 비트맵으로 받아온다. 
//        Bitmap image_bitmap = BitmapFactory.decodeFile(pic_path.get(0));
//        image.setImageBitmap(image_bitmap);
//        Bitmap image_bitmap2 = BitmapFactory.decodeFile(pic_path.get(1));
//        image2.setImageBitmap(image_bitmap2);
//        Bitmap image_bitmap3 = BitmapFactory.decodeFile(pic_path.get(2));
//        image3.setImageBitmap(image_bitmap3);
//        Bitmap image_bitmap4 = BitmapFactory.decodeFile(pic_path.get(3));
//        image4.setImageBitmap(image_bitmap4);
//        Bitmap image_bitmap5 = BitmapFactory.decodeFile(pic_path.get(4));
//        image5.setImageBitmap(image_bitmap5);
//        try {
//            //사진 찍은 날짜 정보 가져오기
//            ExifInterface exif = new ExifInterface(pic_path.get(0));
//            ExifInterface exif2 = new ExifInterface(pic_path.get(1));
//            ExifInterface exif3 = new ExifInterface(pic_path.get(2));
//            ExifInterface exif4 = new ExifInterface(pic_path.get(3));
//            ExifInterface exif5 = new ExifInterface(pic_path.get(4));
//            tv_camera.setText(showExif(exif));
//            tv_camera2.setText(showExif(exif2));
//            tv_camera3.setText(showExif(exif3));
//            tv_camera4.setText(showExif(exif4));
//            tv_camera5.setText(getTime);
//            //Uri에서 이미지 이름을 얻어온다. 
//            if(showExif(exif).equals(getTime)){
//                ok_path.add(pic_path.get(0));
//            }
//            if(showExif(exif2).equals(getTime)){
//                ok_path.add(pic_path.get(1));
//            }
//            if(showExif(exif3).equals(getTime)){
//                ok_path.add(pic_path.get(2));
//            }
//            if(showExif(exif4).equals(getTime)){
//                ok_path.add(pic_path.get(3));
//            }
//            if(showExif(exif5).equals(getTime)){
//                ok_path.add(pic_path.get(4));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//            Log.e("Camera Test", " " + pic_path.get(0));
//        }
//        Log.e("testtest",Integer.toString(ok_path.size()));
//        Log.e("pic_result",Integer.toString(tv_camera.length()));
//        Log.e("pic_result2",Integer.toString(tv_camera5.length()));
//        if(tv_camera.getText().equals(tv_camera5.getText())){
//            Log.e("pic_result3", "ok");
//        }
//    }
//    //URL 에서 파일명 추출
//    public void getImageNameToUri() {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        //사진 최신순으로 정렬해서 가져오기
//        Cursor cursor = getApplicationContext().getContentResolver()
//                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        Log.e("Camera_test2", " " +cursor.getString(column_index));
//        for(int i=0; i<5; i++){
//            pic_path.add(cursor.getString(column_index));
//            cursor.moveToNext(); //다음 사진으로
//        }
//        String imgPath = cursor.getString(column_index);
//        //String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);
//        Log.e("Camera_test", " " + imgPath);
////        return imgPath;
//    }
//
//    // 사진정보에서 찍은날짜 가져오기
//    private String showExif(ExifInterface exif) {
//        String a = exif.getAttribute(ExifInterface.TAG_DATETIME); //사진 정보 가져오기
//        String pic_date;
//        /*
//        날짜가 0이거나 null이면 0으로 받고 아니면 날짜 출력(다운로드한 사진은 null값임)
//         */
//        if (a == null || a.equals("") == true){
//            return "0";
//        }else{
//            pic_date = a.replaceAll(":","");
//            return pic_date.substring(0,8);
//        }
//    }
//}

