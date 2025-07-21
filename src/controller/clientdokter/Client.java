/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.clientdokter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.layout.VBox;

/**
 *
 * @author LENOVO
 */
public class Client {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) {
        try{
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            System.out.println("Error creat");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        } 
    }
    
       public void sendMessageToServer(String messageToServer){
        try{
            bufferedWriter.write(messageToServer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Sending message to the client");
            closeEverything(socket, bufferedReader,bufferedWriter);
        }
    }
       
    public void receiveMessageFromServer(VBox vBox) {
         new Thread(new Runnable(){
            @Override
            public void run(){
                while(socket.isConnected()){
                    try{
                    String messageFromServer = bufferedReader.readLine();
                    sampleController.addLabel(messageFromServer, vBox);
                }catch (IOException e){
                    e.printStackTrace();
                    System.out.println("Error receiving message from the client");
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                    }
                }
            }
        }).start();
    }
    
          
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
         try{
            if(bufferedReader != null){
            bufferedReader.close();
            }
            if(bufferedWriter != null){
            bufferedWriter.close();
            }
            if (socket != null){
            socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
