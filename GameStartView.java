package views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.text.AttributeSet.FontAttribute;

import panels.BoardPanel;

import java.io.*;
import javax.sound.sampled.*;



public class GameStartView extends JFrame{
	
	public GameStartView() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		playAudio();
		
	setTitle("Marvel: Ultimate Wars");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(925, 650);
	setLocationRelativeTo(null);
	//setLayout(new FlowLayout());
	setVisible(true);
	
	
	ImageIcon logo = new ImageIcon("marvellogo.png");
	ImageIcon bg = new ImageIcon("bg2.png");
	this.setIconImage(logo.getImage());
	JLabel background = new JLabel(bg);
	add(background);
	background.setLayout(null);
	background.setSize(925,650);
	background.setHorizontalAlignment(background.CENTER);
	
	
	
	}
	
	 AudioFormat audioFormat;
	  AudioInputStream audioInputStream;
	  SourceDataLine sourceDataLine;
	  final JTextField textField = new JTextField("theme1.wav");
	
	 private void playAudio() {
		 
		 try{
		      File soundFile = new File(textField.getText());
		      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		      audioFormat = audioInputStream.getFormat();
		      //System.out.println(audioFormat);

		      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,audioFormat);

		      sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
		      new PlayThread().start();
		    }catch (Exception e) {
		      e.printStackTrace();
		      System.exit(0);
		    }
		 
		 
	 }

		class PlayThread extends Thread{
		  byte tempBuffer[] = new byte[10000];

		  public void run(){
		    try{
		      sourceDataLine.open(audioFormat);
		      sourceDataLine.start();

		      int cnt;
		      
		      while((cnt = audioInputStream.read(
			   tempBuffer,0,tempBuffer.length)) != -1){
			if(cnt > 0){
			  sourceDataLine.write(tempBuffer, 0, cnt);
			}
		      }
		      sourceDataLine.drain();
		      sourceDataLine.close();
		    }catch (Exception e) {
		      e.printStackTrace();
		      System.exit(0);
		    }
		  }
		}

		

}
