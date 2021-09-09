package com.daotrung.bai1_b5;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPDF;
    FileAdapter adapter;
    ArrayList<FileMode> fileModes;
    ImageButton imgDialog ;
    TextView txtToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        Toolbar toolbar_demo = findViewById(R.id.toolbar);
        txtToolbar = findViewById(R.id.txtToolbar);
        setSupportActionBar(toolbar_demo);
        txtToolbar.setText(toolbar_demo.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rvPDF = findViewById(R.id.rvPDF);
        imgDialog = findViewById(R.id.openDialog);
        fileModes = new ArrayList<FileMode>();
        for (int i = 0; i <= 8; i++) {
            fileModes.add(new FileMode(R.drawable.icon_file, R.drawable.ic_more, "To Kill a Mockingbird " + i));
        }
        adapter = new FileAdapter(fileModes, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvPDF.setAdapter(adapter);
        rvPDF.setLayoutManager(linearLayoutManager);


        // Add
        imgDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.update_lay);

                EditText edtFile = dialog.findViewById(R.id.edtFile);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String fileName = "";
                        if (!edtFile.getText().toString().equals("")) {
                            fileName = edtFile.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please enter file name ", Toast.LENGTH_SHORT).show();
                        }
                        fileModes.add(new FileMode(R.drawable.icon_file,R.drawable.ic_more,fileName));

                        adapter.notifyItemInserted(fileModes.size()-1);
                        rvPDF.scrollToPosition(fileModes.size()-1);
                        dialog.dismiss();
                    }
                    //
                });
                dialog.show();
            }
        });
    }
    // xu ly menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_app:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                rvPDF.setLayoutManager(gridLayoutManager);
                return true;
            case R.id.menu_search:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Hiện tại đây chỉ là bản demo > Chưa có dữ liệu tìm kiếm . Thoát tìm kiếm ?");

                builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // tạo cửa sổ dialog
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);// thiết lập phải chọn , ko chọn thì ko thoát cửa sổ dialog
                // hiển thị cửa sổ này lên
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
