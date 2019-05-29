package edu.buffalo.cse.cse486586.groupmessenger2;

public class HelperClass {

    static String failedProcess = "";
    private static int messageNumber = 0;
    static int sequenceNumber = 0;
    static int processNumber = 0;

    public static void helperInit(String myPort){
        for(int i = 0; i < 5; i++) {
            if(myPort.equals(GroupMessengerActivity.redirectPorts[i]))
                processNumber = i;
        }
    }

    public static String getMessage(String msg){
        ++messageNumber;
        return MessageType.INITIAL.toString() + ";" + sequenceNumber + ";" + "false" + ";" + messageNumber + ";" + msg + ";" + processNumber + ";" + processNumber + ";"+failedProcess+";\n";
    }
}
