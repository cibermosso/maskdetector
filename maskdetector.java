/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maskdetector;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 *
 * @author CIBERCRIMRPPO
 */


        
public class Maskdetector {
    

    public static void query (String ip) throws IOException{
            
        String command = "curl -s http://ip-api.com/json/"+ip+"?fields=61201";
        Process process = Runtime.getRuntime().exec(command);
          BufferedReader stdInput = new BufferedReader(new 
     InputStreamReader(process.getInputStream()));         
        System.out.println(stdInput.readLine());
 
        }
    
    public static void queryNoHunter2 (String ip) throws IOException{
            
        String command = "curl \"http://check.getipintel.net/check.php?ip="+ip+"&contact=fjoseman@gmail.com&format=json&flags=f&oflags=bc&oflags=c";
        Process process = Runtime.getRuntime().exec(command);
          BufferedReader stdInput = new BufferedReader(new 
     InputStreamReader(process.getInputStream()));         
        System.out.println(stdInput.readLine());
 
        }
    
    public static void queryNoHunter (String ip) throws IOException{
            
        String command = "curl https://www.iphunter.info:8082/v1/ip/"+ip+" -H \"X-Key: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.WzE2OSwxNTY0MDc1NDM5LDEwMDBd.pmhk8W5kYT144uYk4EWWaxUI3rb7AI2DYYHpRmeZO1o\"";
        Process process = Runtime.getRuntime().exec(command);
          BufferedReader stdInput = new BufferedReader(new 
     InputStreamReader(process.getInputStream()));         
        System.out.println(stdInput.readLine());
 
        }
    
    public static Duration ping(String host) {
    Instant startTime = Instant.now();
    try {
        InetAddress address = InetAddress.getByName(host);
        if (address.isReachable(1000)) {
            System.out.println(host+" IP ACTIVA");
            return Duration.between(startTime, Instant.now());
            
        }else{System.out.println(host+ "IP NO ACTIVA");
            
        }
    } catch (IOException e) {
        // Host not available, nothing to do here
    }
    return Duration.ofDays(1);
}
    
    public static void proxySOCKS(InetAddress ip, int port) throws MalformedURLException, IOException {

     
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(ip, port));
        URL url = new URL("http://www.google.es");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(2000);
        try {
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                System.out.println("Proxy detectat");
                System.out.println("Http Connection code: " + code);
            } else {
                System.out.println("No proxy");
            }
        } catch (IOException exception) {
            System.out.println("Conexion proxy SOCK NO posible");

        }
    }

    public static void proxyHttp8(InetAddress ip, int port) throws MalformedURLException, IOException {
        URL url = new URL("http://www.google.es");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(2000);
        try {
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                System.out.println("Proxy detectat");
                System.out.println("Http Connection code: " + code);
            } else {
                System.out.println("No proxy");
            }
        } catch (IOException exception) {
            System.out.println("Conexion Proxy Http NO posible");

        }

    }

    public static void rDNS(String ip) throws UnknownHostException {

        InetAddress inetAddress = InetAddress.getByName(ip);
        //String host= ip.getHostName();

        if (!(inetAddress.getHostName().equals(ip) )) {
            System.out.println("Nom: " + inetAddress.getHostName()+"A la ip: "+ip);
        } else {
            System.out.println("Imposible rDNS");
        }
    }
    
 

    public static void main(String[] args) throws ProtocolException, MalformedURLException, IOException, InterruptedException, ExecutionException {

      //  
       // 
        //portScaner("83.37.205.128");
        
JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
     
    
      File archivo = new File (chooser.getSelectedFile().getPath());
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!= null){
            System.out.println(linea);
            queryNoHunter2(linea);
            rDNS(linea);
            ping(linea);
            System.out.println("------------------------------------------------------------------------------------------------------------------\n");
            Thread.sleep(3*1000);
            //portScaner(linea); 
         }
             
      }
      catch(IOException e){
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (IOException e2){ 
         }
      }
   
   
    }
}        
        

   

//InetAddress inetAddress = InetAddress.getByName("83.37.205.128");

        //proxySOCKS(inetAddress, 1972);

       // proxyHttp8(inetAddress, 8080);
public static Future<Boolean> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
  return es.submit(new Callable<Boolean>() {
      @Override public Boolean call() {
        try {
          Socket socket = new Socket();
          socket.connect(new InetSocketAddress(ip, port), timeout);
          socket.close();
          return true;
        } catch (Exception ex) {
          return false;
        }
      }
   });
           
}

public static void portScaner (String ip) throws InterruptedException, ExecutionException{
  final ExecutorService es = Executors.newFixedThreadPool(20);
  final int timeout = 200;
  final List<Future<Boolean>> futures = new ArrayList<>();
  for (int port = 1; port <= 65535; port++) {
    futures.add(portIsOpen(es, ip, port, timeout));
    
  }
  es.shutdown();
  int openPorts = 0;
  for (final Future<Boolean> f : futures) {
    if (f.get()) {
        System.out.println("");
      openPorts++;
    }
  }
  System.out.println("Hi ha " + openPorts + " ports oberts a la ip: " + ip);
  }
    
}
    
    
    


    