package com.example.tonyn.youqiu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private String shezhi;
    private TextView textView;
    private ListView orgList;
    private ArrayList<String> contentList;
    private List<Organization> orgRealList;
    private BmobQuery<Organization> bmobQuery;
    private ArrayAdapter<String> adapter;
    private List<String> orgNameList = new ArrayList<>();//用set可以去重，先写到这里。
    public void baoMing(View view){
        Intent intent = new Intent(MainActivity.this,Main4Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        bmobQuery = new BmobQuery<>();
        //contentList = new ArrayList<>();
        setSupportActionBar(toolbar);
        final Intent intent = getIntent();
        shezhi = intent.getStringExtra("name");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Intent logIntent = getIntent();
        //String orgName = logIntent.getStringExtra("createname");
         adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,orgNameList);
        orgList = (ListView) findViewById(R.id.org_name_list);
        orgList.setAdapter(adapter);

        orgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,EveryOrgActivity.class);
                Organization org = orgRealList.get(position);
                contentList = org.getContentList();
                intent.putStringArrayListExtra("contentlist",contentList);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "我们的QQ群：416259507", Snackbar.LENGTH_LONG)
                        .setAction("点击此处联系我们", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                intent1.setData(Uri.parse("tel:18392171798"));
                                startActivity(intent1);
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        textView = (TextView) headView.findViewById(R.id.thisusername);
        textView.setText(shezhi);
        ActionCollor.addlist(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        orgNameList.clear();
        initOrg();
    }

    private void initOrg() {
        BmobQuery<Organization> query = new BmobQuery<>();
        query.addQueryKeys("orgName");
        query.findObjects(new FindListener<Organization>(){
            @Override
            public void done(List<Organization> list, BmobException e) {
                orgRealList = list;
                if (list != null){
                    for (Organization name:list){
                        String orgname = name.getOrgName();
                        orgNameList.add(orgname);
                    }
                    //orgNameList = removeDuplicate(orgNameList);
                }else{
                    Toast.makeText(MainActivity.this,"当前还没有社区哦",Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private List<String> removeDuplicate(List<String> l) {
        Set<String> set = new HashSet<>();
        List<String> newlist = new ArrayList<>();
        for(String s : l){
            if (set.add(s)) {
                newlist.add(s);
            }
        }
        return newlist;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ActionCollor.finashall();
            return true;
        }else if (id == R.id.action_addorg){
            Intent intent = new Intent(MainActivity.this,LoginOrgActivity.class);
            Intent nameintent = getIntent();
            String createname = nameintent.getStringExtra("name");
            intent.putExtra("creater",createname);
            startActivity(intent);
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this,Main7Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this,Main6Activity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            ActionCollor.finashall();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
