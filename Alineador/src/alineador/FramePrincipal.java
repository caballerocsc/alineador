/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alineador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class FramePrincipal extends javax.swing.JFrame {

    private String secuencia1;
    private String secuencia2;
    private int[][] matriz;
    private int[][] score=new int[5][5];
     
    
    
    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alineador de Secuencias de ADN");

        jButton1.setText("Carga Archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Alinador de Secuencias de ADN");

        jButton2.setText("Alinear");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(0, 234, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String aux="";   
  String texto="";
  try
  {
   /**llamamos el metodo que permite cargar la ventana*/
   JFileChooser file=new JFileChooser();
   file.showOpenDialog(this);
   /**abrimos el archivo seleccionado*/
   File abre=file.getSelectedFile();
 
   /**recorremos el archivo, lo leemos para plasmarlo
   *en el area de texto*/
   if(abre!=null)
   {     
      FileReader archivos=new FileReader(abre);
      BufferedReader lee=new BufferedReader(archivos);
      int cont=1;
      while((aux=lee.readLine())!=null)
      {
          if(!aux.contains(">")){
              if (cont==1) {
                  secuencia1 = aux;
                  convertirSecuencia(secuencia1,1);
                  System.out.println(secuencia1);
              }else if(cont==2){
                  secuencia2=aux;
                  convertirSecuencia(secuencia2,2);
                  System.out.println(secuencia2);
              }
              cont++;
          }
      }
      lee.close();
    }    
   }
   catch(IOException ex)
   {
     JOptionPane.showMessageDialog(null,ex+"" +
           "\nNo se ha encontrado el archivo",
                 "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
    }
        jTextArea1.setText(secuencia1+"\n"+secuencia2);
        llenarEncabezados();
        imprimirMatriz();
    }//GEN-LAST:event_jButton1ActionPerformed

    public int llenarMatriz( int fila, int columna,int cabFila, int cabCol){
        int[] op=new int[3];
        int gap=-5;
        score[1][1]=10;score[1][2]=-1;score[1][3]=-3;score[1][4]=-4;
        score[2][1]=-1;score[2][2]=7;score[2][3]=-5;score[2][4]=-3;
        score[3][1]=-3;score[3][2]=-5;score[3][3]=9;score[3][4]=0;
        score[4][1]=-4;score[4][2]=-3;score[4][3]=0;score[4][4]=8;
        System.out.println(matriz[fila-1][columna-1]+"+"+score[cabFila][cabCol]+'='+matriz[fila-1][columna-1]+score[cabFila][cabCol]);
        op[0]=matriz[fila-1][columna-1]+score[cabFila][cabCol];
        op[1]=matriz[fila][columna-1]+gap;
        op[2]=matriz[fila-1][columna]+gap;
        int temp;
        for (int i = 0; i < op.length; i++) {
            for (int j = 0; j < op.length-1; j++) {
                if(op[j]>op[j+1]){
                 temp=op[j];
                 op[j]=op[j+1];
                 op[j+1]=temp;
                }
            }
        }
        return op[2];
    }
    
    public void llenarEncabezados(){
        int filas =secuencia2.length();
        int columnas=secuencia1.length();
        matriz= new int[filas+2][columnas+2];
        for (int i =2; i< matriz[0].length; i++) {
            matriz[0][i]=Integer.parseInt(String.valueOf(secuencia1.charAt(i-2)));
        }
        for (int i=2; i<matriz.length; i++) {
            matriz[i][0]=Integer.parseInt(String.valueOf(secuencia2.charAt(i-2)));
        }
        int cont=0;
        for(int i=1;i<matriz[0].length;i++){
            matriz[1][i]=cont;
            cont-=5;
        }
        cont=0;
        for(int i=1;i<matriz.length;i++){
            matriz[i][1]=cont;
            cont-=5;
        }
        imprimirMatriz();
        for (int i = 2; i < matriz.length; i++) {
            for (int j = 2; j < matriz[0].length; j++) {
                matriz[i][j]=llenarMatriz(i,j,matriz[i][0],matriz[0][j]);
            }
        }
    }
    public void imprimirMatriz(){
        for (int i = 0; i <matriz.length; i++) {
            String linea="";
            for (int j = 0; j < matriz[0].length; j++) {
                linea=linea+" "+matriz[i][j];
            }
            System.out.println(linea);
        }
    }
    public void convertirSecuencia(String cadena, int dif){
        String cadAux="";
        char A='A';
        char C='C';
        char G='G';
        char T='T';
        for (int i = 0; i < cadena.length(); i++) {
            if ((cadena.charAt(i))==A) {
                cadAux+="1";
            }if ((cadena.charAt(i))==G) {
                cadAux+="2";
            }if ((cadena.charAt(i))==C) {
                cadAux+="3";
            }if ((cadena.charAt(i))==T) {
                cadAux+="4";
            }
        }if (dif==1) {
            secuencia1=cadAux;
        }if (dif==2) {
            secuencia2=cadAux;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
