/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Activities often only have one fragment for the UI (on phones anyway) so this abstract
 * class takes care of the fragment transaction when Activities are created and populated with a
 * single fragment. Just need to inherit from it and override the {@link #createFragment()}
 * method. This is a common Android pattern.
 */
public abstract class ActivitySingleFragment extends Activity {
    public static final int FRAGMENT_ID = R.id.fragmentContainer;

    protected abstract Fragment createFragment();

    //Overridden
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(FRAGMENT_ID);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(FRAGMENT_ID, fragment).commit();
        }
    }
}
