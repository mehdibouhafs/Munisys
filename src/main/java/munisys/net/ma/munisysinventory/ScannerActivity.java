package munisys.net.ma.munisysinventory;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.oem.barcode.BCRConfig.BCRCodeType;
import com.oem.barcode.BCRIntents;
import com.oem.barcode.BCRManager;

import java.util.Arrays;


public class ScannerActivity extends PreferenceActivity {


    private BCRAppBroadcastReceiver mBroadcastReceiver = new BCRAppBroadcastReceiver();

    private BCREnabler mBCREnabler;

    private Preference mBCRTrigger;

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.bcr_demo);

        CheckBoxPreference mBCRCheckBox = (CheckBoxPreference) this.findPreference("bcr_onoff");
        mBCREnabler = new BCREnabler(this.getApplicationContext(), mBCRCheckBox);

        mBCRTrigger = (Preference) findPreference("bcr_trigger");

        mActivity = this;

        IntentFilter filter = new IntentFilter();
        filter.addAction(BCRIntents.ACTION_NEW_DATA);
        this.getApplicationContext().registerReceiver(mBroadcastReceiver, filter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBCREnabler.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBCREnabler.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
        mBCREnabler.destroy();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mBCRTrigger) {
            BCRManager.getDefault().BCRTriggerPress();
        }
        // Let the intents be launched by the Preference manager
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Receiver for misc intent broadcasts
     */
    protected class BCRAppBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BCRIntents.ACTION_NEW_DATA)) {

                Log.d("BCRDemo", "received bcr data intent");

                int id = intent.getIntExtra(BCRIntents.EXTRA_BCR_TYPE, -1);
                byte[] data = intent.getByteArrayExtra(BCRIntents.EXTRA_BCR_DATA);

                final Activity activity = mActivity;
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Barcode:" + id);
                builder.setMessage(new String(data));

                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                            case DialogInterface.BUTTON_POSITIVE:
                                break;
                        }
                        dialog.dismiss();
                    }
                };

                builder.setPositiveButton(android.R.string.ok, onClickListener);
                AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.show();

                //Toast.makeText(mActivity,new String(data), Toast.LENGTH_LONG).show();
            }
        }
    }
}


