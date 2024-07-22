import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.app.Activity;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            NfcV nfcV = NfcV.get(tag);
            try {
                nfcV.connect();
                byte[] response = nfcV.transceive(new byte[]{0x00, 0x20});
                String accountInfo = new String(response); // Giả định đã lấy được thông tin tài khoản
                saveToInternalStorage(accountInfo);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    nfcV.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveToInternalStorage(String data) {
        String filename = "account_info.json";
        try (FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
