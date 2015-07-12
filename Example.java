/*
Copyright 2015 Orlando Aguilar Vivanco

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.*;
import OVUtilities.ModalCalendar;

class Example extends JFrame{
public static ModalCalendar calendar;
    public static void main(String[] vr){
      Example mj= new Example();
        JButton bot=new JButton("Open Calendar");
        Example.calendar = new ModalCalendar(mj);

        bot.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent evento){
          JButton botn=  (JButton) evento.getSource();
              Example.calendar.Show();
              System.out.println(Example.calendar.GetDate());
			  Example.calendar.RestartCalendar();
          }
        });
        Container conten= mj.getContentPane();
        conten.setLayout(new BorderLayout());
        conten.add(bot, BorderLayout.SOUTH);
        conten.add(new JLabel(""), BorderLayout.CENTER);
 
         mj.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                  System.exit(0);
                }
         });

        mj.setSize(800,600);
        mj.setVisible(true);

    }
}