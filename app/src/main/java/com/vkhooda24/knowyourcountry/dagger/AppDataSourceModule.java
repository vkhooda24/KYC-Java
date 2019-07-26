package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSourceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.lang.reflect.Type;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
@Module
public class AppDataSourceModule {

    @Provides
    @Singleton
    static AppDataSource provideAppDataSourceModule() {
        return new AppDataSourceImpl();
    }
}
