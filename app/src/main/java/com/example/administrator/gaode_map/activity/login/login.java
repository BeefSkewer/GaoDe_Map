package com.example.administrator.gaode_map.activity.login;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.gaode_map.R;
import com.example.administrator.gaode_map.db.GaodeOenHelper;
import com.example.administrator.gaode_map.model.User;

/**
 * Created by Administrator on 2017/2/18.
 */

public class login extends Activity  implements LoginView.registerclicklistener,
  RegisterView.confirmClickListener{

     RegisterView registerView;
    LoginView loginView;
     private GaodeOenHelper dbHelper;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        registerView=new RegisterView ();
        dbHelper=new GaodeOenHelper(this,"GaoDe_Map.db",null,1);
        dbHelper.getWritableDatabase();
        loginView=new LoginView();

    }
    //启动注册
       public void onRegisterclick(){
           FragmentManager fm=getFragmentManager();
           FragmentTransaction tx=fm.beginTransaction();
           tx.replace(R.id.layout,registerView).commit();
       }
    //注册确认
    public void confirmClick(){
        if(registerView.con_password.getText().toString().equals(registerView.password
                .getText().toString())){
            User user=new User();
            user.setAccount(registerView.account.getText().toString());
            user.setPassword(registerView.password.getText().toString());
            registerView.gaoDe_mapDB.saveUser(user);
            Toast.makeText(this,"成功注册",Toast.LENGTH_SHORT).show();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction tx=fm.beginTransaction();
            tx.replace(R.id.layout,loginView).commit();

        }else{
            Toast.makeText(this,"两次密码不一致，请在此输入",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
