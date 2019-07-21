package com.vkhooda24.knowyourcountry.dagger;

import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSource;
import com.vkhooda24.knowyourcountry.domain.datasource.AppDataSourceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
@Module
public class AppDataSourceModule {

    @Provides
    @Singleton
    static AppDataSource<String, String> provideAppDataSourceModule() {
        return new AppDataSourceImpl();
    }

    @Provides
    @Singleton
    static AppDataSource<String, Integer> provideIntegerAppDataSourceModule() {
        return new AppDataSourceImpl();
    }

}
