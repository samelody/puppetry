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

import android.os.Bundle;

import com.samelody.puppetry.app.PassiveFragment;

/**
 * @author Belin Wu
 */
public class FragmentLifecycle {
    public void onFragmentCreate(PassiveFragment fragment, Bundle state) {}

    public void onFragmentStart(PassiveFragment fragment) {}

    public void onFragmentResume(PassiveFragment fragment, boolean visible) {}

    public void onFragmentPause(PassiveFragment fragment) {}

    public void onFragmentStop(PassiveFragment fragment) {}

    public void onFragmentStateSave(PassiveFragment fragment, Bundle state) {}

    public void onFragmentDestroy(PassiveFragment fragment) {}
}
