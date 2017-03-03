/*
 * Copyright (c) 2017-present Samelody.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.samelody.sample.puppetry;

import android.app.Application;

import com.samelody.puppetry.lifecycle.ActivityLifecycle;
import com.samelody.puppetry.lifecycle.FragmentLifecycle;
import com.samelody.puppetry.lifecycle.LifecycleFactory;
import com.samelody.puppetry.lifecycle.LifecycleManager;
import com.samelody.puppetry.lifecycle.PresenterLifecycle;
import com.samelody.sample.puppetry.lifecycle.SampleActivityLifecycle;
import com.samelody.sample.puppetry.lifecycle.SamplePresenterLifecycle;
import com.samelody.stathod.Stathod;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

public class Sample extends Application {
    private RefWatcher refWatcher;
    private static Sample instance;

    static {
        LifecycleManager.getInstance().setFactory(new LifecycleFactory() {
            @Override
            public PresenterLifecycle createPresenterLifecycle() {
                return new SamplePresenterLifecycle();
            }

            @Override
            public ActivityLifecycle createActivityLifecycle() {
                return new SampleActivityLifecycle();
            }

            @Override
            public FragmentLifecycle createFragmentLifecycle() {
                return null;
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Stathod.setAppContext(this);
        refWatcher = LeakCanary.install(this);
    }

    public static Sample getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().refWatcher;
    }
}
