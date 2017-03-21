package com.example.administrator.bluetoothstu;

import android.support.v7.app.ActionBarActivity;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends ActionBarActivity {

    private Button On, Off,Visible, list;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        On = (Button)findViewById(R.id.button1);
        Off = (Button)findViewById(R.id.button2);
        Visible = (Button)findViewById(R.id.button3);
        list = (Button)findViewById(R.id.button4);

        lv = (ListView)findViewById(R.id.listView1);
        BA = BluetoothAdapter.getDefaultAdapter();
    }

    public void setOn(View view){
                if(!BA.isEnabled()){
                    Intent turnOn=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    Toast.makeText(getApplicationContext(),"Turn on",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Already on",Toast.LENGTH_SHORT).show();
                }
    }
    public void setOff(View view){
                    BA.disable();
                    Toast.makeText(getApplicationContext(),"Turned off",Toast.LENGTH_LONG).show();
    }
    public void setVisible(View view){
                    Intent geiVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(geiVisible, 0);
    }

    public void getList(View view){
                    pairedDevices = BA.getBondedDevices();
                    ArrayList listA=new ArrayList();
                    for(BluetoothDevice bt: pairedDevices){
                        listA.add(bt.getName());
                    }
                    Toast.makeText(getApplicationContext(),"showing paired Devices",Toast.LENGTH_LONG).show();
                    final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listA);
                    lv.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
