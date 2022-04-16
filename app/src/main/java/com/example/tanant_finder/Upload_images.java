package com.example.tanant_finder;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload_images extends AppCompatActivity {

    private static  final  int PICK_IMAGE_REQUEST=1;
    private Button chooseimg1;
    private Button chooseimg2;
    private Button chooseimg3;
    private Button chooseimg4;
    private Button button_show;
    private EditText imagename1;
    private EditText imagename2;
    private EditText imagename3;
    private EditText imagename4;
    private ProgressBar progressBar1;
    private Uri  imageUri;
    ActivityResultLauncher<String> launcher;
      FirebaseDatabase  database;
      FirebaseStorage storage;
      Button button_upload;
      DatabaseReference mdatabaseref;
      StorageReference mstorageref;
      FirebaseAuth mAuth;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);
        chooseimg1=findViewById(R.id.chooseimg1);
        chooseimg2=findViewById(R.id.chooseimg2);
        chooseimg3=findViewById(R.id.chooseimg3);
        chooseimg4=findViewById(R.id.chooseimg4);
        button_show=findViewById(R.id.button_show);
        imagename1=findViewById(R.id.imagename1);
        imagename2=findViewById(R.id.imagename2);
        imagename3=findViewById(R.id.imagename3 );
        imagename4=findViewById(R.id.imagename4);
        progressBar1=findViewById(R.id.progressbar1);
        database=FirebaseDatabase.getInstance() ;
        storage=FirebaseStorage.getInstance();
        mAuth=FirebaseAuth.getInstance();
//        mstorageref=FirebaseStorage.getInstance().getReference("uploads");
//        mdatabaseref=FirebaseDatabase.getInstance().getReference("uploads");
//        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//            @Override
//            public void onActivityResult(Uri uri) {
//                 final StorageReference reference= storage.getReference()
//                        .child("property_images").child(FirebaseAuth.getInstance().getUid());
//                 reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                     @Override
//                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                         reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                             @Override
//                             public void onSuccess(Uri uri) {
//                                  database.getReference().child("Property Details").child(FirebaseAuth.getInstance().getUid()).setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                      @Override
//                                      public void onSuccess(Void unused) {
//                                          Toast.makeText(Upload_images.this, "image uploaded", Toast.LENGTH_SHORT).show();
//                                      }
//                                  });
//                             }
//                         });
//                     }
//                 });
//            }
//        });


        chooseimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               launcher.launch("image/*");
//               openFileChooser();
            }
        });chooseimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               launcher.launch("image/*");
//               openFileChooser();
            }
        });chooseimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               launcher.launch("image/*");
//               openFileChooser();
            }
        });chooseimg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               launcher.launch("image/*");
//               openFileChooser();
            }
        });



        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Upload_images.this,profile.class));
            }
        });



    }

//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data != null && data.getData() !=null){
//             imageUri=data.getData();
//             StorageReference reference=storage.getReference().child("property_images").child(FirebaseAuth.getInstance().getUid());
//            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(Upload_images.this, "image uploaded", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                             double progress=(100.0*snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
//                              progressBar1.setProgress((int)progress);
//                        }
//                    });
//            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    database.getReference().child("Property Details").child(mAuth.getUid()).child("image1").setValue(uri.toString());
//
//                }
//            });
//
//        }
//    }
//
//   public   void openFileChooser()
//    {
//        Intent intent=new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//         startActivityForResult(intent,PICK_IMAGE_REQUEST);
//    }

//    private String getFileExtension(Uri uri){
//        ContentResolver cR=getContentResolver();
//        MimeTypeMap mime= MimeTypeMap.getSingleton();
//        return  mime.getExtensionFromMimeType(cR.getType(uri));
//    }


//   public  void uploadFile(){
//        if (imageUri !=null){
//            StorageReference fileReference=mstorageref.child(System.currentTimeMillis() +"."+  getFileExtension(imageUri));
//            fileReference.putFile(imageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                      progressBar1.setProgress(0);
//                            Toast.makeText(Upload_images.this, "upload successful", Toast.LENGTH_SHORT).show();
//                            Uploads uploads=new Uploads(file_name.getText().toString().trim(),taskSnapshot.getUploadSessionUri().toString());
//                            String uploadId=mdatabaseref.push().getKey();
//                            mdatabaseref.child(uploadId).setValue(uploads);
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(Upload_images.this,e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                             double progress=(100.0*snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
//                              progressBar1.setProgress((int)progress);
//                        }
//                    });
//
//
//        }
//        else{
//            Toast.makeText(this, "No file is selected", Toast.LENGTH_SHORT).show();
//        }
//
//
//   }

}