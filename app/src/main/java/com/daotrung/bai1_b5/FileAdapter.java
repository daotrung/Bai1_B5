package com.daotrung.bai1_b5;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder>{
    private List mFiles;
    private Context mContext;

    public FileAdapter(List mFiles, Context mContext) {
        this.mFiles = mFiles;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_dong, parent, false);
        FileViewHolder viewHolder = new FileViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FileMode file = (FileMode) mFiles.get(position);
        holder.imgFile.setImageResource(file.getImg1());
        holder.txtTitle.setText(file.getTxtTitle());
        holder.imgMore.setImageResource(file.getImg2());
        // edit
        holder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.update_lay);

                EditText edtUpdate = dialog.findViewById(R.id.edtFile);
                Button btnEdt = dialog.findViewById(R.id.btnAdd);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle);

                btnEdt.setText("Update");
                txtTitle.setText("Update File");
                edtUpdate.setText(((FileMode) mFiles.get(position)).txtTitle);
                btnEdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fileName = "";
                        if (!edtUpdate.getText().toString().equals("")) {
                            fileName = edtUpdate.getText().toString();
                        }else{
                            Toast.makeText(mContext, "Please enter file name ", Toast.LENGTH_SHORT).show();
                        }

                        mFiles.set(position,new FileMode(R.drawable.icon_file,R.drawable.ic_more,fileName));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        // delete
        holder.deleteRv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete ? ")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                  mFiles.remove(position);
                                  notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFile;
        private TextView txtTitle;
        private ImageView imgMore;
        private RelativeLayout deleteRv ;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFile = itemView.findViewById(R.id.imgFile);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgMore = itemView.findViewById(R.id.imgMore);
            deleteRv = itemView.findViewById(R.id.update_rv);

        }
    }
}
