package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import rraya.nearsoft.com.timesheetsapp.R
import javax.inject.Inject


class SplashActivity : AppCompatActivity(), HasSupportFragmentInjector {//TODO: [new] presentation, en el fragemntimpelentamso est aienrfaz

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {//TODO: [new] presentation, impemenatmso el metodo que es devro;ver el dispatcher  injectado
        return fragmentDispatchingAndroidInjector;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);//TODO: [new] presentation, con esto injectamos aqui
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (null == savedInstanceState) {
            val frag = SplashView()
            supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
        }

    }

}
