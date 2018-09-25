package com.framgia.demodagger2.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.vnnht.demodagger2.R;
import com.example.vnnht.demodagger2.databinding.ActivityMainBinding;
import com.framgia.demodagger2.utils.MainApplication;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerMainComponent.builder()
                .appComponent(((MainApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ActivityMainBinding mainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
