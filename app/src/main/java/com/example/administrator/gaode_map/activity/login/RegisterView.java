package com.example.administrator.gaode_map.activity.login;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.gaode_map.R;
import com.example.administrator.gaode_map.db.GaoDe_MapDB;
import com.example.administrator.gaode_map.model.User;

/**
 * Created by Administrator on 2017/2/19.
 */

public class RegisterView extends Fragment implements View.OnClickListener {
   public EditText account;
   public EditText password;
   public EditText con_password;
   public Button confirm;
    public GaoDe_MapDB gaoDe_mapDB=GaoDe_MapDB.getInstance(getActivity());
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.register,null);
        confirm=(Button)view.findViewById(R.id.confirm);
       account=(EditText)view.findViewById(R.id.re_account);
       password=(EditText)view.findViewById(R.id.re_password);
       con_password=(EditText)view.findViewById(R.id.confirm_Password);
       confirm.setOnClickListener(this);
       return view;
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.confirm:
                if(getActivity()instanceof confirmClickListener){
                    ((confirmClickListener)getActivity()).confirmClick();
                }

        }
    }
    public interface confirmClickListener{
        void confirmClick();
    }
}
