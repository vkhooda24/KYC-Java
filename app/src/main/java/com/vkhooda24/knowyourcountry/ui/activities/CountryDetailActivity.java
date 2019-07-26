package com.vkhooda24.knowyourcountry.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vkhooda24.knowyourcountry.R;
import com.vkhooda24.knowyourcountry.app.AppConstants;
import com.vkhooda24.knowyourcountry.constants.IntentKeys;
import com.vkhooda24.knowyourcountry.viewmodel.CountryDetailViewModel;
import com.vkhooda24.utils.StringUtil;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.net.URL;

/**
 * Created by Vikram Hooda on 12/24/18.
 */
public class CountryDetailActivity extends Activity {

//    @Inject //Using @Bind and getting from view model factory, @Provide required for @Inject
    CountryDetailViewModel countryDetailViewModel;

    private String countryName = AppConstants.DEFAULT_COUNTRY_NAME;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        TextView countryNameTextView = findViewById(R.id.countryNameTextView);
        TextView countryNativeNameTextView = findViewById(R.id.countryNativeNameTextView);
        TextView countryDomainTextView = findViewById(R.id.countryTopLevelDomainTextView);
        TextView countryCapitalTextView = findViewById(R.id.countryCapitalTextView);
        TextView countryRegionTextView = findViewById(R.id.countryRegionTextView);
        TextView countrySubRegionTextView = findViewById(R.id.countrySubRegionTextView);
        TextView countryTimeZoneTextView = findViewById(R.id.countryTimeZoneTextView);
        TextView countryPopulationTextView = findViewById(R.id.countryPopulationTextView);
        TextView countryAlphaCodeNameTextView = findViewById(R.id.countryAlphaNameTextView);
        ImageView countryFlagImageView = findViewById(R.id.countryFlagImageView);

        if (getIntent() != null && getIntent().getExtras() != null) {
            countryName = getIntent().getExtras().getString(IntentKeys.COUNTRY_NAME);
        }

        countryNameTextView.setText(countryName);

        compositeDisposable.add(countryDetailViewModel.getCountryDetail(countryName).subscribe(
                countryDetail -> {
                    String domainName = countryDetail.getTopLevelDomain() != null ? countryDetail.getTopLevelDomain()[0] : "";
                    String timezone = countryDetail.getTimezones() != null ? countryDetail.getTimezones()[0] : "";
                    countryNameTextView.setText(countryDetail.getName());
                    countryCapitalTextView.setText(getString(R.string.capital, countryDetail.getCapital()));
                    countryNativeNameTextView.setText(getString(R.string.native_name, countryDetail.getNativeName()));
                    countryDomainTextView.setText(getString(R.string.domain_name, domainName));
                    countryRegionTextView.setText(getString(R.string.region_name, countryDetail.getRegion()));
                    countrySubRegionTextView.setText(getString(R.string.sub_region_name, countryDetail.getSubregion()));
                    countryTimeZoneTextView.setText(getString(R.string.timezone, timezone));
                    countryPopulationTextView.setText(getString(R.string.population, StringUtil.formatNumberWithCommas(countryDetail.getPopulation())));
                    countryAlphaCodeNameTextView.setText(getString(R.string.alpha_code_name, countryDetail.getAlpha3Code()));

                    Glide.with(this)
                            .load(AppConstants.IMAGE_NOT_FOUND_URL)
                            .into(countryFlagImageView);
                },
                Throwable::printStackTrace
        ));
    }
}
