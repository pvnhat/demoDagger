package com.framgia.demodagger2.screen.main;

import android.content.Context;
import com.framgia.demodagger2.data.repository.StudentRepository;
import com.framgia.demodagger2.data.source.remote.Api;
import com.framgia.demodagger2.data.source.remote.StudentRemoteDataSource;
import com.framgia.demodagger2.utils.dagger.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private Context mContext;

    public MainModule(Context context) {
        mContext = context;
    }

    @ActivityScope
    @Provides
    public MainViewModel provideMainViewModel(StudentRepository remoteSource) {
        return new MainViewModel(mContext, remoteSource);
    }

    @ActivityScope
    @Provides
    public StudentRepository provideStudentRepository(Api api) {
        return new StudentRepository(new StudentRemoteDataSource(api));
    }
}
