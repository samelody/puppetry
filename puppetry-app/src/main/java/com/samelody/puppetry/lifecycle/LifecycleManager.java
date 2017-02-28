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
package com.samelody.puppetry.lifecycle;

/**
 * The lifecycle manager of a puppetry application.
 *
 * @author Belin Wu
 */
public final class LifecycleManager implements LifecycleFactory {
    /**
     * The singleton.
     */
    private static final LifecycleManager INSTANCE = new LifecycleManager();

    private static final ActivityLifecycle ACTIVITY_LIFECYCLE = new ActivityLifecycle();
    private static final FragmentLifecycle FRAGMENT_LIFECYCLE = new FragmentLifecycle();
    private static final PresenterLifecycle PRESENTER_LIFECYCLE = new PresenterLifecycle();

    private LifecycleFactory factory;

    private LifecycleManager() {}

    public void setFactory(LifecycleFactory factory) {
        this.factory = factory;
    }

    /**
     * Gets the singleton.
     *
     * @return The singleton
     */
    public static LifecycleManager getInstance() {
        return INSTANCE;
    }

    @Override
    public ActivityLifecycle createActivityLifecycle() {
        if (factory == null) {
            return ACTIVITY_LIFECYCLE;
        }
        ActivityLifecycle lifecycle = factory.createActivityLifecycle();
        return lifecycle == null ? ACTIVITY_LIFECYCLE : lifecycle;
    }

    @Override
    public FragmentLifecycle createFragmentLifecycle() {
        if (factory == null) {
            return FRAGMENT_LIFECYCLE;
        }
        FragmentLifecycle lifecycle = factory.createFragmentLifecycle();
        return lifecycle == null ? FRAGMENT_LIFECYCLE : lifecycle;
    }

    @Override
    public PresenterLifecycle createPresenterLifecycle() {
        if (factory == null) {
            return PRESENTER_LIFECYCLE;
        }
        PresenterLifecycle lifecycle = factory.createPresenterLifecycle();
        return lifecycle == null ? PRESENTER_LIFECYCLE : lifecycle;
    }
}
