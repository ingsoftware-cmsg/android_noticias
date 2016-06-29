package cmsg.cl.noticiascmsg;

/**
 * Created by ocantuarias on 28-06-2016.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cmsg.cl.noticiascmsg.fragments.HoyFragment;
import cmsg.cl.noticiascmsg.fragments.SemanaFragment;
import cmsg.cl.noticiascmsg.fragments.MesFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HoyFragment tab1 = new HoyFragment();
                return tab1;
            case 1:
                SemanaFragment tab2 = new SemanaFragment();
                return tab2;
            case 2:
                MesFragment tab3 = new MesFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}