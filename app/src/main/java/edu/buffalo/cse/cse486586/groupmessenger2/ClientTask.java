package edu.buffalo.cse.cse486586.groupmessenger2;

 /*
        The Client task is similar to what I implemented in SimpleMessenger.


        For sending Object through Client Socket I looked at:
        https://stackoverflow.com/questions/27736175/how-to-send-receive-objects-using-sockets-in-java

     */

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

class ClientTask extends AsyncTask<String, Void, Void> {
    static String[] redirectPorts = {"11108", "11112", "11116", "11120", "11124"}; //5 Ports for 5 AVDs
    static final String TAG = ClientTask.class.getSimpleName();

    @Override
    protected Void doInBackground(String... msgs) {

        String msgToSend = msgs[0];
        Message client = new Message();

        client.setFailedPort(HelperClass.failedProcess);
        int max = 0;
        int proposeSeqNumber = 0;
        for(String remotePort: redirectPorts) {
            try {
                if(!remotePort.equals(HelperClass.failedProcess)) {
                    Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                            Integer.parseInt(remotePort));
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(msgToSend);
                    Log.d(TAG, "Client Receive Message");
                    InputStream is = socket.getInputStream();
                    Log.d(TAG, "Read Line at Client");
                    DataInputStream dis = new DataInputStream(is);
                    String message = dis.readUTF();
                    is.close();
                    dis.close();
                    String[] msg = message.split(";");

                    client.setProposalStatus(msg[0]);
                    client.setSequenceNumber(Integer.parseInt(msg[1]));
                    client.setDeliverable(Boolean.parseBoolean(msg[2]));
                    client.setMessageNumber(Integer.parseInt(msg[3]));
                    client.setMessageToSend(msg[4]);
                    client.setProcessNumber(Integer.parseInt(msg[5]));
                    client.setProposedSequenceNumber(Integer.parseInt(msg[6]));


                    if (client.getProposalStatus().equalsIgnoreCase(MessageType.PROPOSE.toString())) {
                        if (max <= client.getSequenceNumber()) {
                            max = client.getSequenceNumber();
                            proposeSeqNumber = client.getProposedSequenceNumber();
                        }
                        socket.close();
                    }
                }
            }
            catch (IOException e) {
                HelperClass.failedProcess = remotePort;
            }/*
            Got the idea to track failed Process in exception from one of the post in Piazza about AVD msg tracking.
            */

        }
        client.setSequenceNumber(max);
        client.setProposalStatus(MessageType.ACCEPTSEQNUMBER.toString());
        client.setProposedSequenceNumber(proposeSeqNumber);

        for(String remotePort: redirectPorts) {
            try {
                if(!remotePort.equals(HelperClass.failedProcess)) {

                    client.setFailedPort(HelperClass.failedProcess);
                    Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                            Integer.parseInt(remotePort));
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF(client.getFullMessage());
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    String message = dis.readUTF();
                    if(message.equalsIgnoreCase(MessageType.ACCEPTED.toString())) {
                        dos.flush();
                        dos.close();
                        socket.close();
                    }
                }
            }
            catch (IOException e) {
                HelperClass.failedProcess = remotePort;
            }

        }
        return null;

    }
}