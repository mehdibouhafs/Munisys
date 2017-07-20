package munisys.net.ma.munisysinventory;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import munisys.net.ma.munisysinventory.adapters.SpinnerAdapter;

/**
 * Created by mehdibouhafs on 18/07/2017.
 */

public class NewClient extends HomeActivity {

    private static final int SELECT_PICTURE = 100;
    private ImageView logo;
    private String path_logo;
    private static final String TAG = "NewClient";
    private EditText input_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.add_client, frameLayout);
        activityId = R.layout.add_client;


        input_client = (EditText) view.findViewById(R.id.input_client);
        input_client.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        Button btn_creer = (Button)view.findViewById(R.id.btn_create);
        Button btn_annuler = (Button)view.findViewById(R.id.btn_annuler);

        logo = (ImageView) view.findViewById(R.id.logo);
        //upload.setVisibility(View.GONE);

        Button btn_upload = (Button)view.findViewById(R.id.upload);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });


        btn_creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String client1 = input_client.getText().toString();
                Log.e("PATH LOGO = ",getPath_logo());
                if(!getPath_logo().isEmpty()) {
                    int clientId = db.insererClient2(client1,getPath_logo());
                    Toast.makeText(getApplicationContext(), "Nouveau Client Ajouter", Toast.LENGTH_SHORT).show();
                    setResult(200);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Veuillez ajouter un Logo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(200);
                finish();
            }
        });
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    void openImageChooser() {
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);*/
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(i, "Select Logo"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    path_logo = selectedImageUri.toString();
                    setPath_logo(selectedImageUri.toString());
                    Log.i(TAG+"uri"," path = "+selectedImageUri.toString());
                    // Set the image in ImageView
                    // upload.setVisibility(View.VISIBLE);

                    logo.setVisibility(View.VISIBLE);
                    logo.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public String getPath_logo() {
        return path_logo;
    }

    public void setPath_logo(String path_logo) {
        this.path_logo = path_logo;
    }
}
