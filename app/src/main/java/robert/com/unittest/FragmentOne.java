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

/**
 * Created by robert on 3/17/18.
 */

public class FragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_back_stack_one, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        Button gotoFragmentTwoBtn = (Button) retView.findViewById(R.id.fragment_back_stack_one_button);
        gotoFragmentTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
/*            public void onClick(View view) {
                FragmentUtil.performNoBackStackTransaction(fragmentManager, "Fragment Two", new FragmentTwo());
            }*/
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                /*if (fragmentTwo != null) {
                    fragmentTransaction.remove(fragmentTwo);
                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment Two exist in back stack, will hide it now.");
                }*/

                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                /*
                 * IMPORTANT: We use the "R.id.fragment_back_stack_frame_layout" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentTwo(), "Fragment Two");
                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                //fragmentTransaction.addToBackStack("Fragment Two");//Keep least one instance of FragmentTwo in back stack


                fragmentTransaction.addToBackStack(null);//Keep least one instance of FragmentTwo in back stack

                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }


            /*public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                if (fragmentTwo != null) {
                    fragmentTransaction.remove(fragmentTwo);
                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment Two exist in back stack, will hide it now.");
                }


                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentTwo(), "Fragment Two");

                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }*/

            /*public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                if (fragmentTwo != null) {
                    fragmentTransaction.remove(fragmentTwo);
                    Log.w(FragmentUtil.TAG_NAME_FRAGMENT, "Fragment Two exist in back stack, will hide it now.");
                }


                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, new FragmentTwo(), "Fragment Two");
                // Add fragment one in back stack.So it will not be destroyed. Press back menu can pop it up from the stack.
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }*/

            /*public void onClick(View view) {

                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                if (fragmentTwo == null) {
                    fragmentTwo = new FragmentTwo();
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.fragment_back_stack_frame_layout, fragmentTwo, "Fragment Two");

                // Add fragment one in back stack.So it will not be destroyed. Press back menu can pop it up from the stack.
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }*/
        });

        return retView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(FragmentUtil.TAG_NAME_FRAGMENT, "--->FragmentOne.onResume()..." + (getView() != null ? "root view is not null" : " root view is NULL"));
        if (getParentFragment() != null) {
            getParentFragment().onResume();
        }
    }
}
