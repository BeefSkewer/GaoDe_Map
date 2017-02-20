package com.example.administrator.gaode_map.activity.login;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.gaode_map.MainActivity;
import com.example.administrator.gaode_map.R;
import com.example.administrator.gaode_map.db.GaoDe_MapDB;
import com.example.administrator.gaode_map.model.User;

/**
 * Created by Administrator on 2017/2/19.
 */

public class LoginView extends Fragment implements View.OnClickListener {
    public EditText accountEdit;
    public EditText passwordEdit;
    public CheckBox remember_pw;
    public CheckBox auto_login;
    public Button login;
    public Button register;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.loginview,null);
        accountEdit=(EditText)view.findViewById(R.id.account);
        login=(Button)view.findViewById(R.id.login);
        register=(Button)view.findViewById(R.id.register);
        passwordEdit=(EditText)view.findViewById(R.id.password);
        remember_pw=(CheckBox)view.findViewById(R.id.remember_pw);
        final GaoDe_MapDB gaoDe_mapDB=GaoDe_MapDB.getInstance(getActivity());
        auto_login=(CheckBox)view.findViewById(R.id.auto_login);
        register.setOnClickListener(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isRemember=preferences.getBoolean("rememver_pw",false);
        if(isRemember){
            String account=preferences.getString("account","");
            String password=preferences.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remember_pw.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                User user=gaoDe_mapDB.loaduser(account);
                if(password.equals(user.getPassword())){
                    editor=preferences.edit();
                    if(remember_pw.isChecked()){
                        editor.putBoolean("rememver_pw",true);
                        editor.putString("account",account);
                        editor.putString("password",password);

                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getActivity(),"account or password is " +
                            "invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void onClick(View v){
         if(getActivity()instanceof registerclicklistener){
             ((registerclicklistener)getActivity()).onRegisterclick();
         }
    }
    public interface registerclicklistener{
         void onRegisterclick();
    }
}
