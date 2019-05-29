package edu.buffalo.cse.cse486586.groupmessenger2;

import java.io.Serializable;
public class Message implements Serializable {

    private String messageToSend;
    private String proposalStatus;
    private int messageNumber;
    private int processNumber;
    private int proposedSequenceNumber;
    private boolean isDeliverable;
    private int sequenceNumber;
    private String failedPort;

    public Message(){
        this.messageToSend = "";
        this.isDeliverable = false;
        this.failedPort = "";
    }


    public String getFullMessage() {
        String fullMsg;
        fullMsg = proposalStatus + ";"+ sequenceNumber + ";" + isDeliverable + ";" + messageNumber + ";" + messageToSend + ";" + processNumber + ";" + proposedSequenceNumber +";"+failedPort+";\n";
        return fullMsg;
    }

    /*
        Setters and Getter Methods
     */

    public void setFailedPort(String failedPort){
        this.failedPort = failedPort;
    }

    public String getFailedPort(){
        return this.failedPort;
    }

    public void setMessageToSend(String messageToSend){
        this.messageToSend = messageToSend;
    }

    public String getMessageToSend(){
        return this.messageToSend;
    }

    public void setProposalStatus(String proposalStatus){
        this.proposalStatus = proposalStatus;
    }

    public String getProposalStatus(){
        return this.proposalStatus;
    }

    public void setMessageNumber(int messageNumber){
        this.messageNumber = messageNumber;
    }

    public int getMessageNumber(){
        return this.messageNumber;
    }

    public void setProcessNumber(int processNumber){
        this.processNumber = processNumber;
    }

    public int getProcessNumber(){
        return this.processNumber;
    }

    public void setProposedSequenceNumber(int proposedSequenceNumber){
        this.proposedSequenceNumber = proposedSequenceNumber;
    }

    public int getProposedSequenceNumber(){
        return this.proposedSequenceNumber;
    }

    public void setDeliverable(boolean deliverable){
        this.isDeliverable = deliverable;
    }

    public boolean getDeliverable(){
        return this.isDeliverable;
    }

    public void setSequenceNumber(int sequenceNumber){
        this.sequenceNumber = sequenceNumber;
    }

    public int getSequenceNumber(){
        return this.sequenceNumber;
    }
}