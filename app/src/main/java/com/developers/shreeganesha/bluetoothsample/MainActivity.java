package com.developers.shreeganesha.bluetoothsample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    ListView listView;
    BluetoothAdapter adapter;
    BluetoothManager manager;
    List savedList;
    Set<BluetoothDevice> pairedDevices;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        aSwitch = findViewById(R.id.Switch);
        listView = findViewById(R.id.ListView);

        manager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();
        savedList = new ArrayList();






    }

    public  void SwichClicked(View view){

        if (!aSwitch.isChecked()){
            adapter.disable();
            Toast.makeText(this, "Off", Toast.LENGTH_SHORT).show();


        }else {
            adapter.enable();
            aSwitch.isChecked();
            pairedDevices = adapter.getBondedDevices();
            for (BluetoothDevice bt : pairedDevices) {

                savedList.add(bt.getName());
            }
            Adapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, savedList);
            listView.setAdapter((ListAdapter) adapter1);

            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
        }
    }
}
