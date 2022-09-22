package app.serverOperations;

import app.NetworkConnection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hakim
 */
public class ReaderWriterServer implements Runnable {

    //running on server
    private String name;
    private HashMap<String, Information> clientList;
    private NetworkConnection nc;

    public ReaderWriterServer(String name, HashMap<String, Information> clientList, NetworkConnection nc) {
        this.name = name;
        this.clientList = clientList;
        this.nc = nc;
    }

    @Override
    public void run() {
        String actualMessage = nc.read();
        System.out.println("Actual Message : " + actualMessage);

        if (actualMessage.toLowerCase().contains("list")) {
            System.out.println("List asked.." + actualMessage);
            String words[] = actualMessage.split("\\$");
            /*
                words[0] = Sender Name
                words[1] = Receiver Name
                words[2] = keyword
                words[3] = message/null
             */
            System.out.println("Client List: \n" + clientList);
            Information info = clientList.get(words[0]);
            String msgToSend = "List of Clients...\n";
            for (Map.Entry<String, Information> entry : clientList.entrySet()) {
                String key = entry.getKey();
                //Information value = entry.getValue();
                msgToSend = msgToSend + key + "\n";
                //System.out.println(key);
            }
            System.out.println("sending.." + msgToSend);
            System.out.println("words0: " + words[0]);
            info.getNcConnection().write(msgToSend);
            //String messageToSend=username+" -> "+sendMsg;
            //Data data=new Data();
            //data.message=messageToSend;
        }
        if (actualMessage.toLowerCase().contains("ip")) {
            String words[] = actualMessage.split("\\$");
            /*
                words[0] = Sender Name
                words[1] = Receiver Name
                words[2] = keyword = ip
                words[3] = message/null
             */
            System.out.println("Client List: \n" + clientList);
            Information info = clientList.get(words[0]);
            String msgToSend = "Your PORT: \n";
            msgToSend += info.getNcConnection().getSocket().getLocalAddress().getHostAddress();
            System.out.println("sending.." + msgToSend);
            System.out.println("words0: " + words[0]);
            info.getNcConnection().write(msgToSend);
        }
        if (actualMessage.toLowerCase().contains("send")) {
            String words[] = actualMessage.split("\\$");
            /*
                words[0] = Sender Name
                words[1] = Receiver Name
                words[2] = keyword = send
                words[3] = message
             */
            Information info = clientList.get(words[1]);//Receiver
            String msgToSend = words[0] + " says: " + words[3];
            System.out.println("sending.." + msgToSend);
            System.out.println("words0: " + words[0]);
            info.getNcConnection().write(msgToSend);
        }
    }

}
