package rraya.nearsoft.com.timesheetsapp.confirmation.dagger

import dagger.Module
import dagger.Provides
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationView
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationViewPresenterContract
import rraya.nearsoft.com.timesheetsapp.dagger.scopes.FragmentScope
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import rraya.nearsoft.com.timesheetsapp.splashloginscreen.ConfirmationPresenter

@Module
class ConfirmationFragmentModule {

    @FragmentScope
    @Provides
    internal fun provideConfirmationPresenter(giphyRepository: IGifsRepository, view: ConfirmationViewPresenterContract.View): ConfirmationViewPresenterContract.Presenter {
        return ConfirmationPresenter(giphyRepository, view)
    }

    @FragmentScope
    @Provides
    internal fun provideConfirmationView(view: ConfirmationView): ConfirmationViewPresenterContract.View {
        return view
    }

}