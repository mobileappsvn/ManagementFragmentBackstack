package robert.com.unittest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by robert on 3/17/18.
 */

public class FragmentThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_back_stack_three, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        Button gotoFragmentOneBtn = (Button) retView.findViewById(R.id.fragment_back_stack_three_button);
        gotoFragmentOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity().getApplicationContext()).create();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Get fragment two if exist.
                Fragment fragmentOne = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment One");
                if (fragmentOne != null) {
                    fragmentTransaction.remove(fragmentOne);
                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment One exist in back stack, will hide it now.");
                }

                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentOne(), "Fragment One");

                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }

            /*public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity().getApplicationContext()).create();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Get fragment two if exist.
                Fragment fragmentOne = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment One");
                if (fragmentOne != null) {
                    fragmentTransaction.remove(fragmentOne);
                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment One exist in back stack, will hide it now.");
                }

                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentOne(), "Fragment One");

                // Do not add fragment three in back stack.
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }*/

            /*public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity().getApplicationContext()).create();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Get fragment one if exist.
                Fragment fragmentOne = new FragmentOne();

                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, fragmentOne, "Fragment One");

                // Do not add fragment three in back stack.
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }*/
        });

        return retView;
    }
}
