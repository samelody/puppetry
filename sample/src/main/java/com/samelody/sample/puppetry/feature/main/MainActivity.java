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
package com.samelody.sample.puppetry.feature.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.samelody.puppetry.passive.PassiveActivity;
import com.samelody.puppetry.Contract;
import com.samelody.sample.puppetry.R;
import com.samelody.sample.puppetry.feature.main.MainContract.Presenter;
import com.samelody.sample.puppetry.route.SampleRouterImpl;
import com.samelody.stathod.Stathod;

public class MainActivity
        extends PassiveActivity<Presenter>
        implements MainContract.PassiveView {

    private Button hi;
    private Button exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        hi = (Button) findViewById(R.id.hi);
        exit = (Button) findViewById(R.id.exit);
        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onHiClick();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onExitClick();
            }
        });
    }

    @Override
    public Contract.Router getRouter() {
        return new SampleRouterImpl(this);
    }

    @Override
    public void setHiClickable(boolean clickable) {
        hi.setClickable(clickable);
    }

    @Override
    public void toastHi() {
        Toast.makeText(this, "Hello world!" + Stathod.getAppContext(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Presenter createPresenter() {
        MainPresenter presenter = new MainPresenter();
        presenter.setModel(new MainModel());
        return presenter;
    }
}
