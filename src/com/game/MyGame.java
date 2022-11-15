package com.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dwivedi
 */
public class MyGame extends JFrame implements ActionListener {
    JLabel heading, clockLabel;
    Font font = new Font("",Font.BOLD,40);
    JPanel mainPanel;
    JButton []btns = new JButton[9];
    
    // game instance variable
    int gameChanses[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int winner =2;
    MyGame(){
        System.out.println("careating instance of game");
        setTitle("My Tic Tac Toe Game");
        
        setSize(850,850);
        ImageIcon icon = new ImageIcon("src/img/icon.png.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        creteGUI();
         setVisible(true);
        
                
    }
    public void creteGUI(){
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        
        // north heading..
        heading = new JLabel("Tic Tc Toe");
        heading.setIcon(new ImageIcon("src/img/icon.png.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
         heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);
        
        //creting object of Jlabel for clock;
         clockLabel = new JLabel("clock");
          clockLabel.setForeground(Color.white);
         clockLabel.setFont(font);
         clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
         this.add(clockLabel,BorderLayout.SOUTH);
         
         
        Thread t = new Thread(){
            public void run(){
                try{
                    while(true){
                        String datetime = new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
         t.start();
         
        // panel section
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        for(int i=1; i<=9; i++){
            JButton btn = new JButton();
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));        }
        this.add(mainPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton)e.getSource();
        String nameStr = currentButton.getName();
        int name = Integer.parseInt(nameStr.trim());
        if(gameChanses[name]==2){
            if(activePlayer==1){
                currentButton.setIcon(new ImageIcon("src/img/1.png.jpg"));
                gameChanses[name]=activePlayer;
                activePlayer=0;
            }
            else{
                currentButton.setIcon(new ImageIcon("src/img/0.png.png"));
                gameChanses[name]=activePlayer;
                activePlayer=1;
            }
            // winner logic
            for(int []temp:wps){
                if((gameChanses[temp[0]]==gameChanses[temp[1]]) &&
                        (gameChanses[temp[1]]==gameChanses[temp[2]]) && gameChanses[temp[2]]!=2){
                    winner = gameChanses[temp[0]];
                    JOptionPane.showMessageDialog(null,"player"+winner+"has won the gme");
                    int i=JOptionPane.showConfirmDialog(this,"do you want to play more?..");
                    if(i==0){
                       this. setVisible(false);
                       new MyGame();
                        
                    }
                    else if(i==1){
                        System.exit(34256);
                    }
                    else{
                        
                    }
                    System.out.println(i);
                    break;
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"this position occupied..");
        }
    }
}