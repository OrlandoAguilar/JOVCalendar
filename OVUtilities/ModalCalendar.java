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

package OVUtilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.util.*;
import javax.swing.*;

public class ModalCalendar extends JDialog {
    private JTextField textField;
    JOVCalendario calendar;
    Frame padre;
	private static final long serialVersionUID = 7526472295622776147L;
	
	
    public ModalCalendar(Frame padree) {
      super(padree, true);
      padre=padree;

        //setTitle("Mete un dato");
        //setUndecorated(true);
        calendar = new JOVCalendario();
        getContentPane().add(calendar.GetPanel());
        setSize(310,300);
        setLocationRelativeTo(padre);
        JButton bot=new JButton("Accept");
        bot.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent evento){
              if (GetDate()==null)    {
					javax.swing.JOptionPane.showMessageDialog(
					padre,
					"Choose the day");
              }else
              setVisible(false);
          }
        });
        getContentPane().add(bot,BorderLayout.SOUTH);

    }
       public void Show(){
         setLocationRelativeTo(padre);
         setVisible(true);
       }

       public void Show(int x, int y){
         setLocationRelativeTo(padre);
         setLocation(x,y);
         setVisible(true);
       }

       public Date GetDate (){
         return calendar.getDate();
       }
	   public void RestartCalendar(){
		   calendar.RestartCalendar();
	   }

}
