package ntnk.sample.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

//        Log.i("Broadcast Example", "SCREEN ON");

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }

            Log.i("Broadcast Example", smsMessageStr);

            //save to file
            saveToFile(context, smsMessageStr);
            Log.i("Broadcast Example", smsMessageStr);
        }
    }


    public void saveToFile(Context context, String message) {

        FileOutputStream fileOutputStreamoutS = null;
        OutputStreamWriter outputStreamWriter = null;

        try {

            fileOutputStreamoutS = context.openFileOutput("message_broadcast_receiver.txt", Context.MODE_PRIVATE);
            outputStreamWriter = new OutputStreamWriter(fileOutputStreamoutS);
            outputStreamWriter.write(message);
            outputStreamWriter.flush();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            try {
                outputStreamWriter.close();
                fileOutputStreamoutS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
