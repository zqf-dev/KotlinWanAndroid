package com.zqf.kotlinwanandroid.ui.act

import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.base.BaseAct
import com.zqf.kotlinwanandroid.databinding.ActivityMainBinding
import com.zqf.kotlinwanandroid.ui.act.fg.*
import com.zqf.kotlinwanandroid.ui.contact.MainContact
import com.zqf.kotlinwanandroid.ui.presenter.MainActivityPresenter
import com.zqf.kotlinwanandroid.util.ActRouter
import com.zqf.kotlinwanandroid.widget.FixFragmentNavigator
import com.zqf.kotlinwanandroid.widget.NoDoubleClickListener

class MainActivity : BaseAct<ActivityMainBinding, MainActivityPresenter>(), MainContact.MainView {

    private lateinit var navController: NavController
    lateinit var mainBnv: BottomNavigationView
    private var exitTime: Long = 0

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun getPresenter(): MainActivityPresenter {
        return MainActivityPresenter(this)
    }

    override fun initV() {
        mTitleBar.setBackIvImage(0)
        mTitleBar.setCentreTitle(getString(R.string.main_fg_home_str))
        mTitleBar.setRightIvVisible(true)
        mTitleBar.setRightIvImage(R.mipmap.search)
        val mainFly = supportFragmentManager.findFragmentById(R.id.main_fly)
        mainBnv = findViewById(R.id.main_bnv)
        if (mainFly != null) {
            navController = NavHostFragment.findNavController(mainFly)
            //创建自定义的Fragment导航器
            val fragmentNavigator =
                FixFragmentNavigator(mContext, mainFly.childFragmentManager, mainFly.id)
            //获取导航器提供者
            val provider = navController.navigatorProvider
            //把自定义的Fragment导航器添加进去
            provider.addNavigator(fragmentNavigator)
            //手动创建导航图
            val navGraph: NavGraph = initNavGraph(provider, fragmentNavigator)
            //设置导航图
            navController.graph = navGraph
            //底部导航设置点击事件
            mainBnv.setOnNavigationItemSelectedListener { item: MenuItem ->
                navController.navigate(item.itemId)
                setTitleText(item.itemId)
                true
            }
            cancelLongClick()
        }
    }

    private fun cancelLongClick() {
        mainBnv.getChildAt(0).findViewById<View>(R.id.navigation_home)
            .setOnLongClickListener { true }
        mainBnv.getChildAt(0).findViewById<View>(R.id.navigation_system)
            .setOnLongClickListener { true }
        mainBnv.getChildAt(0).findViewById<View>(R.id.navigation_official)
            .setOnLongClickListener { true }
        mainBnv.getChildAt(0).findViewById<View>(R.id.navigation_qa).setOnLongClickListener { true }
        mainBnv.getChildAt(0).findViewById<View>(R.id.navigation_me).setOnLongClickListener { true }
        mTitleBar.setRightIvClickListener(object : NoDoubleClickListener() {
            override fun onNoDoubleClick(view: View?) {
                if (mainBnv.selectedItemId == R.id.navigation_me) {
                    //查询是否登录->跳转至消息页

                } else {
                    //跳转至搜索页
                    ActRouter.ofAct(mContext, SearchActivity().javaClass)
                }
            }
        })
    }

    private fun initNavGraph(
        provider: NavigatorProvider,
        fragmentNavigator: FixFragmentNavigator
    ): NavGraph {
        val navGraph = NavGraph(NavGraphNavigator(provider))
        //首页
        val mHomeFgCDestination = fragmentNavigator.createDestination()
        mHomeFgCDestination.id = R.id.navigation_home
        mHomeFgCDestination.className = HomeFragment::class.java.canonicalName!!
        navGraph.addDestination(mHomeFgCDestination)
        //广场
        val mSysCDestination = fragmentNavigator.createDestination()
        mSysCDestination.id = R.id.navigation_system
        mSysCDestination.className = SystemFragment::class.java.canonicalName!!
        navGraph.addDestination(mSysCDestination)
        //公众号
        val mOfficialCDestination = fragmentNavigator.createDestination()
        mOfficialCDestination.id = R.id.navigation_official
        mOfficialCDestination.className = OfficialFragment::class.java.canonicalName!!
        navGraph.addDestination(mOfficialCDestination)
        //问答
        val mQaCD = fragmentNavigator.createDestination()
        mQaCD.id = R.id.navigation_qa
        mQaCD.className = QaFragment::class.java.canonicalName!!
        navGraph.addDestination(mQaCD)
        //我的
        val mMeCDestination = fragmentNavigator.createDestination()
        mMeCDestination.id = R.id.navigation_me
        mMeCDestination.className = MeFragment::class.java.canonicalName!!
        navGraph.addDestination(mMeCDestination)
        navGraph.startDestination = R.id.navigation_home
        return navGraph
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun hasTitleBar(): Boolean {
        return true
    }
}