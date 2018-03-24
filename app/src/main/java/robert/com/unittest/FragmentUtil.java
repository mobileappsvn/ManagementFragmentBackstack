package robert.com.unittest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

/**
 * Created by robert on 3/17/18.
 */

public class FragmentUtil {

    public final static String TAG_NAME_FRAGMENT = "FragmentUtil";

    // Get exist Fragment by it's tag name.
    public static Fragment getFragmentByTagName(FragmentManager fragmentManager, String fragmentTagName) {

        // Get all Fragment list.
        List<Fragment> fragmentList = fragmentManager.getFragments();

        if (fragmentList == null) return null;
        Log.i(TAG_NAME_FRAGMENT, "*********************getFragmentByTagName.size=" + fragmentList.size());
        for (Fragment fragment: fragmentList) {

            if (fragment != null) {
                String fragmentTag = fragment.getTag();
                Log.d(TAG_NAME_FRAGMENT, "--->getFragmentByTagName()=" + fragmentTag + "|fragment=" + fragment.getClass().getSimpleName());
                // If Fragment tag name is equal then return it.
                if (fragmentTag.equals(fragmentTagName)) {
                    return fragment;
                }
            }
        }


        return null;
    }

    // Print fragment manager managed fragment in debug log.
    public static void printActivityFragmentList(FragmentManager fragmentManager) {

        // Get all Fragment list.
        List<Fragment> fragmentList = fragmentManager.getFragments();
        if (fragmentList == null) return;
        Log.i(TAG_NAME_FRAGMENT, "*********************printActivityFragmentList.size=" + fragmentList.size());
        for (Fragment fragment: fragmentList) {

            if (fragment != null) {
                String fragmentTag = fragment.getTag();
                Log.d(TAG_NAME_FRAGMENT, "--->printActivityFragmentList()=" + fragmentTag + "|fragment=" + fragment.getClass().getSimpleName());
            }
        }

        Log.i(TAG_NAME_FRAGMENT, "***********************************fragmentList.size()=" + fragmentList.size());

    }

    public static void performNoBackStackTransaction(final FragmentManager fragmentManager, String tag, Fragment fragment) {
        final int newBackStackLength = fragmentManager.getBackStackEntryCount() +1;

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_back_stack_frame_layout, fragment, tag)
                .addToBackStack(tag)
                .commit();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int nowCount = fragmentManager.getBackStackEntryCount();
                Log.e(TAG_NAME_FRAGMENT, "-->fragmentManager.nowCount=" + nowCount + "|newBackStackLength=" + newBackStackLength);
                if (newBackStackLength != nowCount) {
                    // we don't really care if going back or forward. we already performed the logic here.
                    fragmentManager.removeOnBackStackChangedListener(this);
                    if ( newBackStackLength > nowCount) { // user pressed back
                        fragmentManager.popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                }
            }
        });
    }
    public static void clearFragmentByTag(final FragmentManager fragmentManager, String tag) {
        try {
            for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--) {
                String backEntry = fragmentManager.getBackStackEntryAt(i).getName();
                Log.e(TAG_NAME_FRAGMENT, "-->fragmentManager.backEntry=" + backEntry);
                if (backEntry.equals(tag)) {
                    fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("!====Popbackstack error : " + e);
            e.printStackTrace();
        }
    }


}
