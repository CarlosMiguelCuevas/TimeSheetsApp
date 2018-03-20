package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationViewPresenterContract
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.ActivityViewScope
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.ConfirmationPresenter

@Module
class ConfirmationModule {

    @ActivityViewScope
    @Provides
    internal fun provideConfirmationPresenter(giphyRepository: IGifsRepository): ConfirmationViewPresenterContract.Presenter {
        return ConfirmationPresenter(giphyRepository)
    }
}