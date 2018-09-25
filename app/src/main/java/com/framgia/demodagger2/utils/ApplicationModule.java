package com.framgia.demodagger2.utils;

import android.content.Context;
import com.framgia.demodagger2.utils.dagger.AppScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @AppScope
    public Context provideApplicationContext() {
        return mContext;
    }
}
