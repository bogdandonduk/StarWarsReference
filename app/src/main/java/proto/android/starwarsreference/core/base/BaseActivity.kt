package proto.android.starwarsreference.core.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import bogdandonduk.appbartoolboxandroidlib.SlidrHandler
import bogdandonduk.appbartoolboxandroidlib.appbar.AppBar
import bogdandonduk.appbartoolboxandroidlib.appbar.AppBarHandler
import bogdandonduk.appbartoolboxandroidlib.drawer.AppBarDrawerToggle
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerActivity
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInitializationViewModelHandlerActivity
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrInterface

abstract class BaseActivity<ViewBindingType : ViewBinding, ViewModelType : ViewModel>(
    viewBindingInflation: (layoutInflater: LayoutInflater) -> ViewBindingType,
    override var viewModelInitialization: (Activity) -> ViewModelType
) : BaseViewBindingHandlerActivity<ViewBindingType>(viewBindingInflation),
    SingleAutomaticInitializationWithInitializationViewModelHandlerActivity<ViewModelType>,
    AppBarHandler,
    SlidrHandler {
    override var appBar: AppBar? = null

    override var appBarDrawerToggle: AppBarDrawerToggle? = null

    override var homeAsUpIndicatorView: View? = null

    override var showOptionsMenu: Boolean = true

    abstract var slidable: Boolean
    
    override var slidrInterface: SlidrInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CommonToolbox.registerCurrentActivity(this)
        
        if(slidable)
            slidrInterface = Slidr.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        CommonToolbox.unregisterCurrentActivity()
    }
}