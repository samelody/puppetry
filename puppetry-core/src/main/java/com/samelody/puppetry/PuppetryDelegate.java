/*
 * Copyright (c) 2016 Samelody.
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

package com.samelody.puppetry;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.samelody.puppetry.Puppetry.PassiveView;
import com.samelody.puppetry.Puppetry.Presenter;
import com.samelody.puppetry.Puppetry.Router;

import static java.lang.System.currentTimeMillis;

/**
 * The delegation of the puppetry.
 *
 * @author Belin Wu
 */
public class PuppetryDelegate<P extends Presenter> {

    /**
     * Key of presenter id.
     */
    private static final String KEY_PRESENTER_ID = "puppetry_presenter_id";

    /**
     * The presenter wrapper.
     */
    private PresenterWrapper wrapper;

    /**
     * The presenter id.
     */
    private String presenterId;

    private boolean stateSaveCalled;

    private boolean presenterRemoved;

    public void onViewCreate(Controller<P> factory, Bundle state) {
        if (state == null) {
            presenterId = "presenter-" + currentTimeMillis();
        }
        else {
            presenterId = state.getString(KEY_PRESENTER_ID);
            stateSaveCalled = false;
        }
        wrapper = PresenterManager.getInstance().getPresenter(presenterId, factory);
        if (wrapper.isFresh()) {
            wrapper.getPresenter().create();
        }
    }

    public <V extends PassiveView, R extends Router> void onViewStart(V view, R router) {
        if (wrapper.isFresh()) {
            wrapper.getPresenter().attachView(view, router);
        }
        else {
            wrapper.getPresenter().reattachView(view, router);
        }
        wrapper.getPresenter().start();
    }

    public void onViewStop() {
        wrapper.getPresenter().stop();
        wrapper.getPresenter().detachView();
    }

    public void onStateSave(Bundle state) {
        state.putString(KEY_PRESENTER_ID, presenterId);
        stateSaveCalled = true;
    }

    public void onViewResume() {
        wrapper.getPresenter().resume();
    }

    public void onViewPause() {
        wrapper.getPresenter().pause();
    }

    public void onViewDestroy(Fragment fragment) {
        if (fragment.getActivity().isFinishing()) {
            removePresenter();
        }
        else if (fragment.isRemoving() && !stateSaveCalled) {
            removePresenter();
        }
    }

    public void onViewDestroy(Activity activity) {
        if (activity.isFinishing()) {
            removePresenter();
        }
    }

    /**
     * Gets the presenter.
     *
     * @return The presenter.
     */
    @SuppressWarnings("unchecked")
    public P getPresenter() {
        return (P) wrapper.getPresenter();
    }

    /**
     * Remove the presenter.
     */
    private void removePresenter() {
        if (presenterRemoved) {
            return;
        }
        PresenterManager.getInstance().remove(presenterId);
        wrapper.getPresenter().destroy();
        wrapper.setPresenter(null); // null presenter object
        presenterRemoved = true;
    }
}
