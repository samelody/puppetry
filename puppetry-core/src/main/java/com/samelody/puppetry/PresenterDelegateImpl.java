/*
 * Copyright (c) 2016-present Samelody.com
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

import com.samelody.puppetry.Contract.PassiveView;
import com.samelody.puppetry.Contract.Presenter;
import com.samelody.puppetry.Contract.Router;

import static java.lang.System.currentTimeMillis;

/**
 * The delegation of the presenter.
 *
 * @author Belin Wu
 */
class PresenterDelegateImpl<P extends Presenter> implements PresenterDelegate<P> {

    /**
     * Key of presenter id.
     */
    private static final String KEY_PRESENTER_ID = "puppetry_presenter_id";

    /**
     * The prefix of the presenter id.
     */
    private static final String PRESENTER_ID_PREFIX = "presenter-";

    /**
     * The presenter.
     */
    private AbstractPresenter presenter;

    /**
     * The controller.
     */
    private Controller<P> controller;

    /**
     * The target proxy of presenter.
     */
    private TargetProxy<P> presenterProxy = new TargetProxy<>();

    /**
     * The presenter id.
     */
    private String presenterId;

    private boolean saveStateCalled;

    private boolean presenterRemoved;

    PresenterDelegateImpl(Class<P> presenterInterface) {
        presenterProxy.setTargetInterface(presenterInterface);
    }

    public void onViewCreate(Controller<P> controller, Bundle state) {
        this.controller = controller;
        if (state == null) {
            presenterId = PRESENTER_ID_PREFIX + currentTimeMillis();
        }
        else {
            presenterId = state.getString(KEY_PRESENTER_ID);
            saveStateCalled = false;
        }
        presenter = PresenterManager.getInstance().getPresenter(presenterId, controller);
        presenterProxy.setTarget((P) presenter);
        presenterRemoved = false;
        if (presenter.isFresh()) {
            presenter.create();
        }
    }

    public <V extends PassiveView, R extends Router> void onViewStart(V view, R router) {
        if (presenterRemoved) {
            return;
        }
        if (presenter.isFresh()) {
            presenter.attachView(view, router);
        }
        else {
            presenter.reattachView(view, router);
        }
        presenter.start();
    }

    public void onViewStop() {
        if (presenterRemoved) {
            return;
        }
        presenter.stop();
        presenter.detachView();
    }

    public void onSaveState(Bundle state) {
        if (presenterRemoved) {
            return;
        }
        state.putString(KEY_PRESENTER_ID, presenterId);
        saveStateCalled = true;
    }

    public void onViewResume(boolean isVisibleToUser) {
        if (presenterRemoved) {
            return;
        }
        presenter.resume(isVisibleToUser);
    }

    public void onViewPause() {
        if (presenterRemoved) {
            return;
        }
        presenter.pause();
    }

    public void onViewDestroy(Fragment fragment) {
        if (presenterRemoved) {
            return;
        }
        if (fragment.getActivity().isFinishing()) {
            removePresenter();
        }
        else if (fragment.isRemoving() && !saveStateCalled) {
            removePresenter();
        }
    }

    public void onViewDestroy(Activity activity) {
        if (presenterRemoved) {
            return;
        }
        if (activity.isFinishing()) {
            removePresenter();
        }
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (presenterRemoved) {
            return;
        }
    }

    @Override
    public P createPresenterAndModel() {
        String implName = presenterProxy.getTargetInterface().getName().replace("Contract$", "");
        try {
            AbstractPresenter presenter = (AbstractPresenter) Class.forName(implName).newInstance();
            presenter.createModel();
            return (P) presenter;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the presenter.
     *
     * @return The presenter.
     */
    @SuppressWarnings("unchecked")
    public P getPresenter() {
        return (P) presenterProxy.getTarget();
    }

    /**
     * Remove the presenter.
     */
    private void removePresenter() {
        if (presenterRemoved) {
            return;
        }
        PresenterManager.getInstance().remove(presenterId);
        presenter.destroy();
        presenter = null;
        presenterProxy.setTarget(null);
        presenterRemoved = true;
    }
}
