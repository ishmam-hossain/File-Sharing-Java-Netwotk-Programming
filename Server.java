package FTPServer;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Arrays;




public class Server {

		static String[] userNameList = {"alice","bob","cindy"};
	    static String[] userPassList = {"aaa","bbb","ccc"};

	    public static void main(String[] args) throws Exception {
	    

	            ServerSocket welcomeSocket= new ServerSocket(6723);

	            while(true)
	            {
	                Socket connectionSocket=welcomeSocket.accept();
	                BufferedReader inFromClient=
	                        new BufferedReader(new InputStreamReader(
	                                connectionSocket.getInputStream()));
	                DataOutputStream outToClient=
	                        new DataOutputStream(
	                                connectionSocket.getOutputStream());
	                

	                //System.out.println("Server>> ");
	                String username = inFromClient.readLine();
	                System.out.println("name-> "+username);
	                outToClient.writeBytes("Enter Password: "+'\n');
	                String password= inFromClient.readLine();
	                System.out.println("pass-> "+password);
	                
	                //---------checking ID & PASSWORD---------------
	                
	                boolean found = false, loggedIn = false;
	                for(int i=0;i<userNameList.length;i++)
	                {
	                	if(userNameList[i].equalsIgnoreCase(username) && userPassList[i].equals(password))
	                		found = true;	
	                	
	                }
	                
	                if(found)
	                {
	                	outToClient.writeBytes("Successfully Logged in as "+username+"!"+'\n');
	                	loggedIn=true;
	                
	                }
	                	else
	                	outToClient.writeBytes("Wrong Username or Password !"+'\n');
	                
	                
	                //----------File List reading & sending to Client ---------------------
	                 
	                
	                if (loggedIn && username.equalsIgnoreCase("alice"))
	                {
	                	
	                	 File folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\bob\\files");
	                	 File[] listOfFiles = folder.listFiles();
	                     //filesInCurrentFolder = listOfFiles();
	                     Arrays.sort(listOfFiles);
	                     

	                     for (int i = 0; i < listOfFiles.length; i++)
	                     {
	                           if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(".txt")||listOfFiles[i].toString().contains(".jpg")))
	                           {
	                             //System.out.println("File " + listOfFiles[i].getName()+folder);
	                        	   
	                         	outToClient.writeByte(listOfFiles.length);  
	                            outToClient.writeBytes("Files from BOB >>	" + listOfFiles[i].getName()+'\n');
	                             
	                           }
	                     }
	                     
	                     folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\cindy\\files");
	                     listOfFiles = folder.listFiles();
	                     Arrays.sort(listOfFiles);
	                     
	                     for (int i = 0; i < listOfFiles.length; i++)
	                     {
	                           if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(".txt")||listOfFiles[i].toString().contains(".jpg")))
	                           {
	                             //System.out.println("File " + listOfFiles[i].getName()+folder);
	                         	outToClient.writeByte(listOfFiles.length);  
	                            outToClient.writeBytes("Files from Cindy >>	" + listOfFiles[i].getName()+'\n');
	                             
	                           }
	                     }
						
					}
	                else if (loggedIn && username.equalsIgnoreCase("bob")) {
	                	
	                	 File folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\alice\\files");
	                	 File[] listOfFiles = folder.listFiles();
	                     //filesInCurrentFolder = folder.listFiles();
	                     Arrays.sort(listOfFiles);
	                     

	                     for (int i = 0; i < listOfFiles.length; i++) 
	                     {
	                           if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(".txt")||listOfFiles[i].toString().contains(".jpg")))
	                           {
	                             //System.out.println("File " + listOfFiles[i].getName()+folder);
	                         	outToClient.writeByte(listOfFiles.length);  
	                            outToClient.writeBytes("Files from ALICE >>	" + listOfFiles[i].getName()+'\n');
	                             
	                           }
	                     }
	                     
	                     folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\cindy\\files");
	                     listOfFiles = folder.listFiles();
	                     Arrays.sort(listOfFiles);
	                     
	                     for (int i = 0; i < listOfFiles.length; i++) 
	                     {
	                           if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(".txt")||listOfFiles[i].toString().contains(".jpg")))
	                           {
	                             //System.out.println("File " + listOfFiles[i].getName()+folder);
	                         	outToClient.writeByte(listOfFiles.length);  
	                            outToClient.writeBytes("Files from Cindy >>	" + listOfFiles[i].getName()+'\n');
	                             
	                           }
	                     }
						
					}
	                else if (loggedIn && username.equalsIgnoreCase("cindy")) {
	                	
	                	 File folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\alice\\files");
	                	 File[] listOfFiles = folder.listFiles();
	                     //filesInCurrentFolder = folder.listFiles();
	                     Arrays.sort(listOfFiles);
	                     

	                     for (int i = 0; i < listOfFiles.length; i++)
	                     {
	                           if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(".txt")||listOfFiles[i].toString().contains(".jpg")))
	                           {
	                             //System.out.println("File " + listOfFiles[i].getName()+folder);
	                         	outToClient.writeByte(listOfFiles.length);  
	                            outToClient.writeBytes("Files from ALICE >>	" + listOfFiles[i].getName()+'\n');
	                             
	                           }
	                     }
	                     
	                     folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\bob\\files");
	                     listOfFiles = folder.listFiles();
	                     Arrays.sort(listOfFiles);
	                     
	                     for (int i = 0; i < listOfFiles.length; i++)
	                     {
	                           if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(".txt")||listOfFiles[i].toString().contains(".jpg")))
	                           {
	                             //System.out.println("File " + listOfFiles[i].getName()+folder);
	                         	outToClient.writeByte(listOfFiles.length);  
	                            outToClient.writeBytes("Files from BOB >>	" + listOfFiles[i].getName()+'\n');
	                             
	                           }
	                     }
						
					} //while ends
	                
	                
	                
	                //------------Copying File--------------
	                
	                String clientName = inFromClient.readLine();
	                String fileName = inFromClient.readLine();
	                //System.out.println(clientName+" "+fileName);
	                
	                
	                if (clientName.equalsIgnoreCase("alice") || clientName.equalsIgnoreCase("bob") || clientName.equalsIgnoreCase("cindy") ) {
	                	
	                	File folder = new File("F:\\NET PRO\\Lab5AssignmentFinal\\"+clientName+"\\files");
	                	File[] listOfFiles = folder.listFiles();
	                	listOfFiles = folder.listFiles();
	                    Arrays.sort(listOfFiles);
	                     
	                    for (int i = 0; i < listOfFiles.length; i++)
	                     {
	                    	 
	                    	  if (listOfFiles[i].isFile() && (listOfFiles[i].toString().contains(fileName)))
	                           {
	                           
	                    		  File sourceFile = new File("F:\\NET PRO\\Lab5AssignmentFinal\\"+clientName+"\\files\\"+fileName);
	           	                  File destFile = new File("F:\\NET PRO\\Lab5AssignmentFinal\\"+username+"\\files"+username+".txt");
	                    		  try
	                    		  {
		           	                  Files.copy(sourceFile.toPath(), destFile.toPath());
		           	                  
	                    			  
	                    		  }catch (Exception e) {
									// TODO: handle exception
								  }finally{
									//outToClient.writeBytes("File Copied Succesfully !");
								  }
	                              
	                           }
	                     }
						
					}
	                

	                
	                	
	                
	     } 
	 } 
	            
	                	       
	                
	                
	                
	    //welcomeSocket.close();
	            
}
	 
	    


	  
