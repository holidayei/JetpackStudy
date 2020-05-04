package com.holiday.jetpackstudy.navigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.holiday.jetpackstudy.R;
import com.holiday.jetpackstudy.navigation.ui.dashboard.DashboardFragment;
import com.holiday.jetpackstudy.navigation.ui.home.HomeFragment;
import com.holiday.jetpackstudy.navigation.ui.notifications.NotificationsFragment;

/**
 * https://juejin.im/post/5c53be3951882562d27416c6
 * <p>
 * new act -> bottom navigation act 即可自动创建
 */
// TODO: 2020-04-29 fragment反复创建问题
// TODO: 2020-04-29 封装一套json配置页面的接入方案
public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        //fragment复用
        //获取页面容器NavHostFragment
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        //获取导航控制器
        NavController navController = NavHostFragment.findNavController(fragment);
        //创建自定义的Fragment导航器
        FixFragmentNavigator fragmentNavigator =
                new FixFragmentNavigator(this, fragment.getChildFragmentManager(), fragment.getId());
        //获取导航器提供者
        NavigatorProvider provider = navController.getNavigatorProvider();
        //把自定义的Fragment导航器添加进去
        provider.addNavigator(fragmentNavigator);
        //手动创建导航图
        NavGraph navGraph = initNavGraph(provider, fragmentNavigator);
        //设置导航图
        navController.setGraph(navGraph);
        //底部导航设置点击事件
        navView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });
        //        NavigationUI.setupWithNavController(navView, navController);//这种方式有问题，有待研究
    }

    //手动创建导航图，把3个目的地添加进来
    private NavGraph initNavGraph(NavigatorProvider provider, FixFragmentNavigator fragmentNavigator) {
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        //用自定义的导航器来创建目的地
        FragmentNavigator.Destination destination1 = fragmentNavigator.createDestination();
        destination1.setId(R.id.navigation_home);
        destination1.setClassName(HomeFragment.class.getCanonicalName());
        destination1.setLabel(getResources().getString(R.string.title_home));
        navGraph.addDestination(destination1);

        FragmentNavigator.Destination destination2 = fragmentNavigator.createDestination();
        destination2.setId(R.id.navigation_dashboard);
        destination2.setClassName(DashboardFragment.class.getCanonicalName());
        destination2.setLabel(getResources().getString(R.string.title_dashboard));
        navGraph.addDestination(destination2);

        FragmentNavigator.Destination destination3 = fragmentNavigator.createDestination();
        destination3.setId(R.id.navigation_notifications);
        destination3.setClassName(NotificationsFragment.class.getCanonicalName());
        destination3.setLabel(getResources().getString(R.string.title_notifications));
        navGraph.addDestination(destination3);

        navGraph.setStartDestination(R.id.navigation_home);

        return navGraph;
    }
}
