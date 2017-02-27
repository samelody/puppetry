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
public final class LifecycleManager {
    /**
     * The singleton.
     */
    private static final LifecycleManager instance = new LifecycleManager();

    /**
     * The lifecycle listener.
     */
    private LifecycleListener lifecycleListener;

    /**
     * Gets the singleton.
     *
     * @return The singleton
     */
    public static LifecycleManager getInstance() {
        return instance;
    }

    public void setLifecycleListener(LifecycleListener listener) {
        this.lifecycleListener = listener;
    }

    /**
     * Gets the lifecycle's listener.
     *
     * @return The lifecycle listener
     */
    public static LifecycleListener getLifecycleListener() {
        LifecycleListener listener = getInstance().lifecycleListener;
        if (listener == null) {
            listener = new LifecycleListenerAdapter();
            getInstance().setLifecycleListener(listener);
        }
        return listener;
    }
}
