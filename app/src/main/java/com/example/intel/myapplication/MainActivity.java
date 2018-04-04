package com.example.intel.myapplication;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView productlist;
    TextView tv_data;
    FloatingActionButton addBtn;
    Dialog mDialog;
    ProductAdapter productAdapter;
    EditText nameEt, emailEt, phoneEt;
    Button addlistbtn;
    ArrayList<ProductDetails> mList = new ArrayList<>();
    int selectposition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intilize();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(0);
            }
        });

    }

    void opendialog(int type) {
        mDialog = new Dialog(MainActivity.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.data_enter_list);
        mDialog.setTitle("Details");
        nameEt = (EditText) mDialog.findViewById(R.id.name_edt);
        emailEt = (EditText) mDialog.findViewById(R.id.email_edt);
        phoneEt = (EditText) mDialog.findViewById(R.id.mobile_edt);
        addlistbtn = (Button) mDialog.findViewById(R.id.adddial_btn);

        mDialog.show();

        if (type == 0) {
            addlistbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetails item = new ProductDetails();
                    item.setName(nameEt.getText().toString());
                    item.setEmail(emailEt.getText().toString());
                    item.setMobile(phoneEt.getText().toString());
                    mList.add(item);
                    prepareListDetails();
                    mDialog.dismiss();

                }
            });
        } else {

            ProductDetails item=mList.get(selectposition);
            nameEt.setText(item.getName());
            emailEt.setText(item.getEmail());
            phoneEt.setText(item.getMobile());

            addlistbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ProductDetails updateItem=new ProductDetails();
                    updateItem.setName(nameEt.getText().toString());
                    updateItem.setEmail(emailEt.getText().toString());
                    updateItem.setMobile(phoneEt.getText().toString());

                    mList.set(selectposition,updateItem);
                    prepareListDetails();
                    mDialog.dismiss();

                }
            });
        }


    }

    void intilize() {
        productlist = (RecyclerView) findViewById(R.id.list_recyclerview);
        addBtn = (FloatingActionButton) findViewById(R.id.addbtn);
        tv_data = (TextView) findViewById(R.id.text_data);
    }

    void prepareListDetails() {

        if (mList.size() > 0) {

            tv_data.setVisibility(View.GONE);
            productlist.setVisibility(View.VISIBLE);

            if (productAdapter == null) {
                productAdapter = new ProductAdapter(MainActivity.this, mList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                productlist.setLayoutManager(mLayoutManager);
                productlist.setItemAnimator(new DefaultItemAnimator());
                productlist.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
                productlist.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();

                productAdapter.setOnClickListener(new ProductAdapter.OnClickListener() {
                    @Override
                    public void onLayoutClick(int position) {
                        selectposition = position;
                        opendialog(1);
                    }
                });
            } else {
                productAdapter.setProductList(mList);
                productAdapter.notifyDataSetChanged();
            }
        } else {
            tv_data.setVisibility(View.VISIBLE);
            productlist.setVisibility(View.GONE);
        }

    }
}
