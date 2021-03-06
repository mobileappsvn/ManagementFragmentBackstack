package robert.com.unittest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by robert on 3/17/18.
 */
public class FragmentTwo2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_back_stack_two, container, false);

        final FragmentManager fragmentManager = getFragmentManager();
        TextView description = retView.findViewById(R.id.description);
        description.setText("This is Fragment Two:" + (new Random().nextInt()));

        Log.i(FragmentUtil.TAG_NAME_FRAGMENT, "--->onCreateView...");

        Button gotoFragmentThreeBtn = (Button) retView.findViewById(R.id.fragment_back_stack_two_button);
        gotoFragmentThreeBtn.setText("Go To Fragment Two");
        gotoFragmentThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Log.e(FragmentUtil.TAG_NAME_FRAGMENT, "-->FragmentTwo.onClick().fragmentManager.nowCount=" + fragmentManager.getBackStackEntryCount());
                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                if (fragmentTwo != null) {
                    //fragmentTransaction.hide(fragmentTwo);
                    //fragmentManager.popBackStack("Fragment Two", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment Two exist in back stack, will hide it now.");
                }

                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentTwo2(), "Fragment Two");
                fragmentTransaction.addToBackStack("Fragment Two");
                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);

//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                // Get fragment two if exist.
//                Fragment fragmentThree = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Three");
//                // Because fragment two has been popup from the back stack, so it must be null.
//                if (fragmentThree != null) {
//                    fragmentTransaction.remove(fragmentThree);
//                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment Three exist in back stack, will hide it now.");
//                }
//                // Add Fragment with special tag name.
//                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentThree(), "Fragment Three");
//                fragmentTransaction.addToBackStack("Fragment Three");
//                fragmentTransaction.commit();
//
//                FragmentUtil.printActivityFragmentList(fragmentManager);
            }

        });

        return retView;
    }
}
