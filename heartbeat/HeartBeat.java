import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.sound.sampled.*;
import javax.swing.*;

class XThread implements Runnable {
  Thread runner;
  boolean playing;
  AudioInputStream audioIn;
  Clip clip;
int i;
int count;
  String fileName;
String whichThread;
	XThread(String threadName, String soundFileName) {
whichThread = threadName;
    fileName = soundFileName;
		runner = new Thread(this, threadName); // Initialize thread.
		System.out.println(this);
    playing = false;
		runner.start();
i = 0;
	}

  public void playNow() {
    System.out.println("assigning true to playing!******");
    playing = true;
    System.out.println(playing);
System.out.println("is clip playing?: " + clip.isRunning());
i = 0;
  }

  public void stopPlay(){
    playing = false;
  }

  public void assignFile(String newFileName){
    fileName = newFileName;

  }

	public void run() {
		//Display info about this particular thread
		System.out.println(Thread.currentThread().getName());
    try{
      audioIn = AudioSystem.getAudioInputStream(new File(fileName));
      System.out.println("assign clip: "+ audioIn);
      AudioFormat format = audioIn.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);

      clip = (Clip) AudioSystem.getLine(info);
      // System.out.print("from inside the threadSound: playing: ");
      clip.open(audioIn);
      clip.setFramePosition(0);
      // System.out.println(playing);
      //System.out.println("is clip running in while loop? ----> " + clip.isRunning());
      while(true){
    	  Thread.currentThread().sleep(500);
         //System.out.println("playing? ----> " + playing);
        if(i == 0&&playing&&!clip.isRunning()){
          clip.setFramePosition(0);
          //System.out.println("sound should be playing right now!");
		System.out.println("***********************thread counter: " + i);
          clip.start();
          clip.drain();
		//clip.close();
		i++;
          //Thread.currentThread().sleep(5000);
          System.out.println("is clip running after .start()? --------> " + clip.isRunning());
        }
      }
    }
    catch(Exception ex){
      System.out.println(whichThread+": "+ex);
    }
  }
}

public class HeartBeat {

  public static void main(String[] args) throws IOException{
    FileInputStream inputMail;
    FileInputStream inputHR;
    BufferedReader brMail;
    BufferedReader brHR;
    String lineMail;
    String lineHR;
    String heartRate;

    try {

    	Thread.currentThread().sleep(10000);
 	XThread threadHeart150 = new XThread("threadHeart150", "HeartBeat Sounds/150.wav");
	XThread threadHeart100 = new XThread("threadHeart100", "HeartBeat Sounds/100.wav");
        XThread threadHeart50 = new XThread("threadHeart50", "HeartBeat Sounds/50.wav");
        XThread threadSound = new XThread("threadSound", "Notification Sounds/New Mail.wav");
      while(true){
	
	
	System.out.println("Number of active threads: " + Thread.activeCount());
Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for ( Thread t : threadSet){
            System.out.println("Thread :"+t+":"+"state:"+t.getState());
        }

        inputMail = new FileInputStream("mail data copy.txt");
        inputHR = new FileInputStream("scripts/timestamp_heartrate.txt");
        brMail = new BufferedReader(
        new InputStreamReader(inputMail));
        brHR = new BufferedReader(
        new InputStreamReader(inputHR));
        String timeStampMail = new SimpleDateFormat("MMM dd HH:mm:ss yyyy").format(new java.util.Date());
        String timeStampHR = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        System.out.println(timeStampMail);
        System.out.println(timeStampHR);
        while ((lineMail = brMail.readLine()) != null){
          if(lineMail.equals(timeStampMail)) {
            System.out.println("found it!!!!");
            threadSound.playNow();
          }
        }
        while ((lineHR = brHR.readLine()) != null){
          if(lineHR.contains(timeStampHR)){
            String delims = "[,]";
            String[] lineHR_parsed = lineHR.split(delims);
            System.out.println("heartrate minute found");
            System.out.println("the heartrate is: " + lineHR_parsed[1]);
            heartRate = lineHR_parsed[1];
            if(heartRate.equals("50")){
              System.out.println("50!!!!");
              //threadHeartLow.stopPlay();
		//threadHeartLow = null;
              threadHeart50.playNow();
            }
	if(heartRate.equals("100")){
              System.out.println("100!!!!");
              //threadHeartLow.stopPlay();
		//threadHeartLow = null;
              threadHeart100.playNow();
            }
	if(heartRate.equals("150")){
              System.out.println("150!!!!");
              //threadHeartLow.stopPlay();
		//threadHeartLow = null;
              threadHeart150.playNow();
            }
          }
        }
        //The sleep() method is invoked on the main thread to cause a one second delay.
        Thread.currentThread().sleep(1000);
//threadSound.runner = null;
      }
    }
    catch (Exception ex){
      System.out.println(ex);
    }
  }
}
