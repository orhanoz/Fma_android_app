package fm.com.zo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class CihazKurulumActivity extends AppCompatActivity {
    WifiManager wifi;
    String wifis[];
    WifiScanReceiver wifiReceiver;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cihaz_kurulum);
        lv = (ListView) findViewById(R.id.listView);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiScanReceiver();
        wifi.startScan();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), parent.getAdapter().getItem(position).toString(), Toast.LENGTH_LONG).show();
                final String ssid = parent.getAdapter().getItem(position).toString();
                final String[] passwd = {""};
                final String[] ssidCheck = {""};
                AlertDialog.Builder builder = new AlertDialog.Builder(CihazKurulumActivity.this);
                builder.setTitle("Ağ");
                builder.setMessage("Seçtiğiniz ağ için parola giriniz");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                final EditText input = new EditText(CihazKurulumActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);
                builder.setCancelable(false);
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        passwd[0] = input.getText().toString();
                        Toast.makeText(getApplicationContext(), passwd[0].toString(),Toast.LENGTH_LONG).show();
                        WifiConfiguration conf = new WifiConfiguration();
                        conf.SSID = "\"" + ssid + "\"";
                        conf.preSharedKey = "\""+ passwd[0] +"\"";
                        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
                        wifiManager.addNetwork(conf);
                        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                        for( WifiConfiguration i : list ) {
                            if(i.SSID != null && i.SSID.equals("\"" + ssid + "\"")) {
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(i.networkId, true);
                                wifiManager.reconnect();
                                break;
                            }
                        }
                        dialog.dismiss();
                        ssidCheck[0] =wifiManager.getConnectionInfo().getSSID().toString();
                        Toast.makeText(getApplicationContext(),wifiManager.getConnectionInfo().getSSID().toString(),Toast.LENGTH_LONG).show();
                        if(ssidCheck[0].equals(ssid)){
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(CihazKurulumActivity.this);
                            builder2.setTitle("Ağ Bilgileri");
                            builder2.setMessage("Ev ağınızın adını ve parolasını giriniz");
                            LinearLayout layout = new LinearLayout(CihazKurulumActivity.this);
                            layout.setOrientation(LinearLayout.VERTICAL);
                            builder2.setIcon(android.R.drawable.ic_dialog_info);
                            final EditText input1 = new EditText(CihazKurulumActivity.this);
                            final EditText input2 = new EditText(CihazKurulumActivity.this);
                            input1.setHint("Ağ ismi");
                            input1.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_NORMAL);
                            layout.addView(input1);
                            input2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            layout.addView(input2);
                            builder2.setView(layout);
                            builder2.setCancelable(false);
                            builder2.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //TODO tcp yada istenen yöntem ile alinan veri gönderilecek
                                    Toast.makeText(getApplicationContext(),input1.getText().toString(),Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(),input2.getText().toString(),Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            });
                            builder2.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder2.create().show();
                        }
                    }
                });
                builder.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }


    protected void onPause() {
        unregisterReceiver(wifiReceiver);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    private class WifiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = wifi.getScanResults();
            wifis = new String[wifiScanList.size()];
            for (int i = 0; i < wifiScanList.size(); i++) {
                wifis[i] = ((wifiScanList.get(i).SSID).toString());
            }
            lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, wifis));
        }
    }

}
