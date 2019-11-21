package com.example.amal.esa.ui.passrenewal;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amal.esa.R;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.ui.services.PathUtil;
import com.example.amal.esa.utility.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class PassRenewalFragment extends Fragment {
    private static final int SELECT_VIDEO = 3;

    private String selectedPath;
    private PassRenewalViewModel mViewModel;
    Button mUpload;
    TextView chooseFile;

    public static PassRenewalFragment newInstance() {
        return new PassRenewalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_pass_renewal, container, false);

        chooseFile=rootView.findViewById(R.id.choose_file);
        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });
        mUpload=rootView.findViewById(R.id.upload);
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedPath!=null &&!chooseFile.getText().toString().equals("")) {
                    File file=new File(selectedPath);
                    uploadNach(file);
                }
                else{
                    Toast.makeText(getActivity(),"Please select your document",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PassRenewalViewModel.class);
        // TODO: Use the ViewModel
    }



    private void chooseFile() {
        /*Intent intent = new Intent();
        intent.setType("/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a File "), SELECT_VIDEO);*/


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    SELECT_VIDEO);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getActivity(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }



    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }



    private void uploadNach(File file) {

        // System.out.println("=======file======="+file.getAbsolutePath());

        SharedPrefManager sharedPrefManager = new SharedPrefManager();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);




       /* progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        // progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setMax(100);
        progressDialog.setMessage("Please wait... ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.setProgress(0);

        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);*/


        SharedPrefManager sharedPrefManager1 = new SharedPrefManager();
        String token = sharedPrefManager1.getUserDetail(getActivity()).token;
        System.out.println("=====token=====" + token);
        Map<String, String> map1 = new HashMap<>();
        map1.put("Authorization", "Token " + token);

        String fileName=file.getName();

        final RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

        // ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);


        // System.out.println("===========ff===="+sharedPrefManager.getUserDetail(getActivity()).data.org_id);
        final HashMap<String, RequestBody> map = new HashMap<>();
        map.put("title", ApiClient.createRequestBody(fileName));

        ApiInterface mApiService = ApiClient.getInterfaceService(getActivity());

        Call<ResponseBody> mService = mApiService.postImage(map1,fileToUpload, map);
        mService.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        System.out.println("========result==" + result);
                        JSONObject jsonObject = new JSONObject(result);
                        String msg = jsonObject.getString("message");
                        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                        chooseFile.setText("");
                    }catch (Exception e){

                    }
                }
                else{
                    try {
                        String errorBody= response.errorBody().string();
                        System.out.println("======errorbody===="+errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

                //System.out.println("============" + t.getMessage());
                call.cancel();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO) {
                System.out.println("SELECT_VIDEO");
                Uri selectedImageUri = data.getData();
                System.out.println("=======SelectedImageUri==="+selectedImageUri);
                //selectedPath = getPath(selectedImageUri);

             /*   Uri uri = data.getData();
                File file = new File(uri.getPath());//create path from uri
                final String[] split = file.getPath().split(":");//split the path.
                filePath = split[1];//assign it to a string(your choice).*/
               // The above code will work in oreo and if it is below oreo than PathUtil will work.Thanks!

                try {
                    selectedPath= PathUtil.getPath(getActivity(),selectedImageUri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                chooseFile.setText(selectedPath);
            }
        }
    }
}
