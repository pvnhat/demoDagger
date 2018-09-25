package com.framgia.demodagger2.screen.list;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.vnnht.demodagger2.R;
import com.example.vnnht.demodagger2.databinding.ActivityListStudentBinding;
import com.framgia.demodagger2.utils.MainApplication;
import javax.inject.Inject;

public class ListStudentActivity extends AppCompatActivity {

    @Inject
    ListViewModel mListViewModel;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ListStudentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DaggerListStudentComponent.builder()
                .appComponent(((MainApplication) getApplication()).getAppComponent())
                .listStudentModule(new ListStudentModule(this))
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        ActivityListStudentBinding activityListStudentBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_list_student);
        activityListStudentBinding.setViewModel(mListViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mListViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mListViewModel.onStop();
        super.onStop();
    }
}
