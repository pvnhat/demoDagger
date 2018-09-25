package com.framgia.demodagger2.utils;

        import android.content.Context;
        import com.framgia.demodagger2.data.source.remote.Api;
        import com.framgia.demodagger2.data.source.remote.NetworkModule;
        import com.framgia.demodagger2.utils.dagger.AppScope;
        import dagger.Component;

@AppScope
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface AppComponent {

    Context applicationContext();

    Api nameApi();
}
