package robert.com.unittest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by robert on 3/17/18.
 */

public class FragmentBackStackActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_back_stack);

        setTitle("Fragment Back Stack Example.");

        // Get FragmentManager and FragmentTransaction object.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create FragmentOne instance.
        FragmentOne2 fragmentOne = new FragmentOne2();

        // Add fragment one with tag name.
        fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, fragmentOne, "Fragment One");
        //fragmentTransaction.addToBackStack("Fragment One");
        //fragmentTransaction.addToBackStack(null);// neu mo dong nay thi khi back se ko bi null neu ve toi man hinh FragmentOne
        fragmentTransaction.commit();

        //FragmentUtil.performNoBackStackTransaction(fragmentManager, "Fragment One", new FragmentOne2());

    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.e(FragmentUtil.TAG_NAME_FRAGMENT, "-->onBackPressed().fragmentManager.nowCount=" + fragmentManager.getBackStackEntryCount());
        if(fragmentManager.getBackStackEntryCount() > 1) {
            //If FragmentTwo push tag is null
            //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            //If FragmentTwo push tag is "Fragment Two"
            fragmentManager.popBackStack("Fragment Two", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            //fragmentManager.popBackStackImmediate("Fragment One", 0);
        } else {
            super.onBackPressed();
        }
        //FragmentUtil.printActivityFragmentList(fragmentManager);
        //FragmentUtil.clearFragmentByTag(fragmentManager,"Fragment Two");
        //confirmExit();
    }

    public void confirmExit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //Set dialog message
        alertDialogBuilder
                .setTitle(getResources().getString(R.string.title_confirm_exit))
                .setMessage(getResources().getString(R.string.message_confirm_exit))
                .setCancelable(true)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentBackStackActivity2.super.onBackPressed();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
        });

        //Create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEUTRAL).setEnabled(false);
            }
        });
        //Show it
        alertDialog.show();
    }
}