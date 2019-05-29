package edu.buffalo.cse.cse486586.groupmessenger2;

import java.util.Comparator;


/*
    I used the below tutorial to use Priority Queue

    https://www.callicoder.com/java-priority-queue/
 */

public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message msgOne, Message msgTwo)
    {
        if(msgOne.getSequenceNumber() == msgTwo.getSequenceNumber()) {
            return msgOne.getProposedSequenceNumber() - msgTwo.getProposedSequenceNumber();
        }
        else
        {
            return msgOne.getSequenceNumber()-msgTwo.getSequenceNumber();
        }
    }
}
