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

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Point;
import java.awt.event.*;


public class MJTable extends JTable{
DefaultTableModel modelo = new DefaultTableModel();
     JScrollPane scroll;
     Point Punto;
     int filaM=0,columnaM=0;
     Object obSel=null;
     public MJTable(){                           //Crea la tabla
           scroll = new JScrollPane(this);
           rowSelectionAllowed=false;
           showHorizontalLines =false;
           showVerticalLines = false;
           setModel(modelo);
           setDragEnabled(false);
           this.addMouseListener(new MouseAdapter()
           {
             public  void mousePressed(MouseEvent e)
              {
                 int fila = rowAtPoint(e.getPoint());
                 int columna = columnAtPoint(e.getPoint());

                 if (getVal(fila,columna).toString().isEmpty()){
                   changeSelection(filaM, columnaM, false, false);
                   obSel=getVal(filaM,columnaM);
                 } else{
                   filaM=fila;
                   columnaM=columna;
                   changeSelection(filaM, columnaM, false, false);
                   obSel=getVal(fila,columna);
                 }

              }
           });
      }

      public void define(String a){            //agrea una nueva columna
               modelo.addColumn(a);
      }


       public void agrega(Object [] fila){    //agrega una fila usando un arreglo de objetos con todo lo que la fila tiene
         modelo.addRow(fila);

       }

       public void refresca(){                  //refresca lo que se ve. Debe llamarse la primera vez.
         setModel(modelo);
       }
       public void limpia(){                    //limpia lo que hay en la tabla
              modelo = new DefaultTableModel();
              refresca();
       }
       public JScrollPane GetPanel(){           //Retorna el panel
              return(scroll);
       }

       public void cambia(String valor,int fila,int columna){
         setValueAt (valor, fila, columna); // Cambia el valor de la fila 1, columna 2.

       }
       public void cambia(String valor,int cant){
         setValueAt (valor, cant/getColumnCount(), cant%getColumnCount()); // Cambia el valor de la fila 1, columna 2.

       }

       public Point selClick(){
              return Punto;
       }

       public boolean isCellEditable (int row, int column)
       {
           return false;
       }
       public Object  getVal(int fila, int columna){
              return getValueAt(fila, columna);
       }
       
        public Object  getValSel(){
//              if (getVal(filaM,columnaM).toString().isEmpty())

              return obSel;
       }

	   public void setValSel(Object val){
		   obSel=val;
	   }
	   

       public void selecciona(int fila,int columna){
         filaM=fila;
         columnaM=columna;
         changeSelection(filaM, columnaM, false, false);
       }
}