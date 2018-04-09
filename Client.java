package FTPClient;

import java.io.*;
import java.net.*;


public class Client {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
            String user;
            String pass;

            BufferedReader inFromUser=
                    new BufferedReader(new InputStreamReader(System.in));

            //InetAddress inetAddress=InetAddress.getLocalHost();
            Socket clientSocket = new Socket("localhost",6723);
            DataOutputStream outToServer=
                    new  DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer=
                    new  BufferedReader(new InputStreamReader
                            (clientSocket.getInputStream()));

            System.out.println("Client>>");
            System.out.print("Enter Username: "+'\n');
            user = inFromUser.readLine();
            outToServer.writeBytes(user+'\n');
            
            String message = inFromServer.readLine();
            
            System.out.print(message+'\n');
            pass = inFromUser.readLine();
            outToServer.writeBytes(pass+'\n');
            
            message = inFromServer.readLine();
            System.out.println(message);
            
            
            //----------Viewing File List------------- 

            System.out.println('\n');
            for(int i=0;i<2;i++)
            {
            	int len = inFromServer.read();
                for(int j=0;j<len;j++){
                	String fileList = inFromServer.readLine();
                	if(fileList != null)
                		System.out.println(fileList);


            	
                }
            }
  
           
            //-------------Copying Files from other Clients---------------
            
            System.out.print("\n\nEnter C to copy Files from other Clients: ");
            String wantToCopy = inFromUser.readLine();
            if ( wantToCopy.equalsIgnoreCase("c") ) 
            {
            	System.out.print("Enter Client name: ");
            	String clientName = inFromUser.readLine();
            	System.out.println("Enter File name: ");
            	String fileName = inFromUser.readLine();
            	
            	outToServer.writeBytes(clientName+'\n');
            	outToServer.writeBytes(fileName+'\n');
            	
				
			}else{
				System.out.println("Wrong Keyword ! ");
			}
            
               
            
            
            clientSocket.close();
            




	}

}

