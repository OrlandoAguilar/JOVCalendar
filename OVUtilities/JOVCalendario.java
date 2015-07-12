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

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.*;


public class JOVCalendario implements ActionListener{
     int anio;
     int mes;
     int dia;
     Calendar calendar;
     DateFormat dateFormat;
     Date trialTime=null;
     JPanel panel;
     MJTable mtab;
     Object[] semana= new Object[7];
     JButton atras;
     JButton adelante;
     JTextField jtx;
     JTextField JTFAnio;
     JButton arriba;
     JButton abajo;
     public static final String[] Meses={"January","February","March","April","May","June","July","August","September","October","November","December"};

  JOVCalendario(){
     panel=new JPanel();
     panel.setLayout (new BorderLayout());
     mtab=new MJTable();
     mtab.define("Su");
     mtab.define("Mo");
     mtab.define("Tu");
     mtab.define("We");
     mtab.define("Th");
     mtab.define("Fr");
     mtab.define("Sa");

     for (int z=0;z<7;z++)
         semana[z]="hola";

     for (int z=0;z<6;z++)
        mtab.agrega(semana);
     mtab.refresca();
     panel.add (mtab.GetPanel(), BorderLayout.CENTER);

     
     //primera parte del box
     JPanel box1= new JPanel();
     box1.setLayout(new BoxLayout(box1,BoxLayout.Y_AXIS));

     //Parte del anio
     JPanel flow1= new JPanel();
     flow1.setLayout(new FlowLayout());                             //ver lo del boxlayout
     
     JPanel box2= new JPanel();
     box2.setLayout(new GridLayout (2,1));


     arriba=new JButton("^");
     arriba.addActionListener (this);
     box2.add(arriba);
     abajo=new JButton("v");
     abajo.addActionListener (this);
     box2.add(abajo);

     JTFAnio=new JTextField("",7);
     JTFAnio.addActionListener (this);
     //JTFAnio.setEditable(false);
     flow1.add(JTFAnio);
     flow1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT );
     //agregar la parte del anio
     
     flow1.add(box2); //poner los dos botones
     box1.add(flow1);

     JPanel flow2= new JPanel();
     flow2.setLayout(new FlowLayout());
     //agregar la parte del anio


     //PArte de la seleccion del mes
     JPanel Bag1= new JPanel();
     GridBagConstraints c = new GridBagConstraints();
     c.fill = GridBagConstraints.BOTH;
     c.anchor = GridBagConstraints.CENTER;

     c.weighty = 0.0;
     c.weightx = 0.0;
     c.gridx=0;
     c.gridy=0;
     c.anchor = GridBagConstraints.WEST;
     atras=new JButton("<");
     atras.addActionListener (this);
     Bag1.add(atras,c);

     c.weightx = 1;
     c.fill =GridBagConstraints.HORIZONTAL;
     c.gridwidth =1;
     c.gridx=1;
     jtx=new JTextField("",16);
     jtx.setEditable(false);

     Bag1.add(jtx,c);

     c.fill =GridBagConstraints.NONE;
     c.gridwidth =0;
     c.weightx = 0.0;
     c.gridx=2;
     c.anchor = GridBagConstraints.EAST;
     adelante=new JButton(">");
     adelante.addActionListener (this);
     Bag1.add(adelante,c);

     flow2.add(Bag1);
     box1.add(flow2);

     //Agrega la parte de los botones del calendario
     panel.add (box1, BorderLayout.NORTH);
    
     //Se agraga la tabla
     panel.add (mtab.GetPanel(), BorderLayout.CENTER);


     //Propiedades del calendario
     calendar = new GregorianCalendar();
     calendar.setFirstDayOfWeek(Calendar.SUNDAY );
     dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     trialTime = new Date();
     calendar.setTime(trialTime);
     anio=calendar.get(Calendar.YEAR);
     mes=calendar.get(Calendar.MONTH)+1;
     dia=calendar.get(Calendar.DAY_OF_MONTH);
     
     
     mtab.selecciona((dia+calendar.get(Calendar.DAY_OF_WEEK)-1)/7,(dia+calendar.get(Calendar.DAY_OF_WEEK)-1)%7);
     /* System.out.println((dia+calendar.get(Calendar.DAY_OF_WEEK)-1)/7 );
     System.out.println((dia+calendar.get(Calendar.DAY_OF_WEEK)-1)%7); */

     
     JTFAnio.setText(""+anio);
     jtx.setText(Meses[mes-1]);
     actualizaCalendario();
  }
  
  public void cambiaFecha(int diap, int mesp, int aniop){
       try{
           trialTime = dateFormat.parse(""+aniop+"-"+mesp+"-"+diap);
           calendar.setTime(trialTime);
       }catch(Exception ex){
       }
  }
  public JPanel GetPanel(){
    return panel;
  }
  public void actualizaCalendario(){
    cambiaFecha(1, mes, anio);

    int primerDiaMes=calendar.get(Calendar.DAY_OF_WEEK)-1;
    int diasDelMes=calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+primerDiaMes;
    int z,cont;
    for (z=0;z<primerDiaMes;z++)
         mtab.cambia("",z);
    for (cont=0;z<diasDelMes;z++){
         cont++;
         mtab.cambia(""+cont,z);
    }
    for (;z<42;z++)
         mtab.cambia("",z);
  }
	public void RestartCalendar(){
		mtab.setValSel(null);
	}
  public Date getDate(){
    try{
    dia=Integer.parseInt(mtab.getValSel().toString());
    if (dia>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+calendar.get(Calendar.DAY_OF_WEEK)-1)
       return null;
    return dateFormat.parse(""+anio+"-"+mes+"-"+dia);
    }catch(Exception e){
    }
    return null;
  }

  public void actionPerformed(ActionEvent evento){
        if (evento.getSource() instanceof JButton){
           JButton boton = (JButton) evento.getSource();
           if(((String)boton.getText()).equals("^") ){
               anio++;
               JTFAnio.setText(""+anio);
			   RestartCalendar();
            }else if (((String)boton.getText()).equals("v") ){
               anio--;
               JTFAnio.setText(""+anio);
			   RestartCalendar();
            }else if (((String)boton.getText()).equals(">") ){
               mes++;
               if (mes>12)
                  mes=1;
               jtx.setText(Meses[mes-1]);
			   RestartCalendar();
            }else if (((String)boton.getText()).equals("<") ){
               mes--;
               if (mes<1)
                  mes=12;
               jtx.setText(Meses[mes-1]);
			   RestartCalendar();
            } //else
      }else if (evento.getSource() instanceof JTextField){
          JTextField campo = (JTextField) evento.getSource();
          if(campo.equals(JTFAnio) ){
             try{
             anio=Integer.parseInt(JTFAnio.getText());
             }catch (Exception e){
             }
          }//else
      }
      actualizaCalendario();
  }
}