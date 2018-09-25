package com.framgia.demodagger2.screen.list;

import android.content.Context;
import com.framgia.demodagger2.data.repository.StudentRepository;
import com.framgia.demodagger2.data.source.remote.Api;
import com.framgia.demodagger2.data.source.remote.StudentRemoteDataSource;
import com.framgia.demodagger2.utils.dagger.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ListStudentModule {
    private Context mContext;

    public ListStudentModule(Context context) {
        mContext = context;
    }

    @ActivityScope
    @Provides
    public ListViewModel provideListViewModel(StudentRepository repository,
            ListStudentAdapter adapter) {
        return new ListViewModel(mContext, repository, adapter);
    }

    @ActivityScope
    @Provides
    public ListStudentAdapter provideListStudentAdapter() {
        return new ListStudentAdapter();
    }

    @ActivityScope
    @Provides
    public StudentRepository provideStudentRepository(Api api) {
        return new StudentRepository(new StudentRemoteDataSource(api));
    }
}
