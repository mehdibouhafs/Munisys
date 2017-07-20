package munisys.net.ma.munisysinventory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.util.Log;
import com.oem.barcode.BCRIntents;
import com.oem.barcode.BCRManager;

public class BCREnabler implements Preference.OnPreferenceChangeListener {
	private static final String LOG_TAG = "BCREnabler";

    private final Context mContext;
    
    private final CheckBoxPreference mCheckBoxPref;
    
    BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	Log.w(LOG_TAG, "BCR received Intent: " + intent.getAction());
            if (intent.getAction().equals(BCRIntents.ACTION_STATE_CHANGED)) {
            	onBCRStateChanged();
            } 
        }
    };




    public BCREnabler(Context context, CheckBoxPreference checkBoxPreference) {
        
        mContext = context;
        mCheckBoxPref = checkBoxPreference;
        
        checkBoxPreference.setPersistent(false);
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(BCRIntents.ACTION_STATE_CHANGED);
        context.registerReceiver(mIntentReceiver, filter);
        
    }

    public void resume() {
        
        mCheckBoxPref.setChecked(BCRManager.getDefault().BCRGetStatus() == 1);
        mCheckBoxPref.setOnPreferenceChangeListener(this);
    }
    
    public void pause() {
        mCheckBoxPref.setOnPreferenceChangeListener(null);
        
    }
    
    public void destroy() {
    	mContext.unregisterReceiver(mIntentReceiver);
    }

    private void onBCRStateChanged() {
        mCheckBoxPref.setChecked(BCRManager.getDefault().BCRGetStatus() == 1);
    }
    
    /**
     * Called when someone clicks on the checkbox preference.
     */
    public boolean onPreferenceChange(Preference preference, Object newValue) {
    	if(((Boolean) newValue)) {
    		BCRManager.getDefault().BCREnable();
    	}else {
    		BCRManager.getDefault().BCRDisable();
    	}
        return true;
    }

}
