
package Mancala;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jonathan Nicio
 */

class Tablero {
    private JPanel tablero;
    private JPanel filaFichas1;
    private JFrame principal;
    private JButton B1;
    private JButton B2;
    private JButton B3;
    private JButton B4;
    private JButton B5;
    private JButton B6;
    private int[] receptaculos ={4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4,4, 4, 0};;
    int iniciales = 4;
    int contadorJ1=0;
    int contadorJ2=0;
    private int posPC=0;
    private int posicion;
    private String[] botones ={"No", "Si"};
    private JLabel [] fichas = new JLabel[14];

    Tablero(){
        oyenteMenu oido = new oyenteMenu();
        principal = new JFrame("Mancala");
        tablero= new JPanel();
        filaFichas1 = new JPanel();
        JPanel filaFichas2 = new JPanel();
        JMenuBar jMBBarra = new JMenuBar();
        B1= new JButton(Integer.toString(receptaculos[0]));
        B1.addActionListener(oido);
        B2= new JButton(Integer.toString(receptaculos[1]));
        B2.addActionListener(oido);
        B3= new JButton(Integer.toString(receptaculos[2]));
        B3.addActionListener(oido);
        B4= new JButton(Integer.toString(receptaculos[3]));
        B4.addActionListener(oido);
        B5= new JButton(Integer.toString(receptaculos[4]));
        B5.addActionListener(oido);
        B6= new JButton(Integer.toString(receptaculos[5]));
        B6.addActionListener(oido);

        for(int h=0; h<fichas.length; h++){
            fichas[h] = new JLabel("hola",SwingConstants.CENTER);
        }
        for(int i=0; i<fichas.length; i++){
            fichas[i].setText(Integer.toString(receptaculos[i]));
        }
    }

    void crearJuego(){
        filaDeFichasJugador1();
        tablero.setLayout(new GridLayout(1, 3));
        tablero.add(fichas[13]);
        tablero.add(filaFichas1);
        tablero.add(fichas[6]);
        principal.add(tablero);
        principal.setResizable(false);
        principal.setVisible(true);
        principal.pack();
        principal.setLocationRelativeTo(null);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void filaDeFichasJugador1(){
        filaFichas1.setLayout(new GridLayout(2, 6));

        for (int i = 7; i < 13; i++) {
            filaFichas1.add(fichas[i]);
        }

        filaFichas1.add(B1);
        filaFichas1.add(B2);
        filaFichas1.add(B3);
        filaFichas1.add(B4);
        filaFichas1.add(B5);
        filaFichas1.add(B6);

    }

    private String prueba(int pos){
        String valorn = Integer.toString(receptaculos[pos]);
        return valorn;
    }

    private int tiroPc(){

        computadora pc1 = new computadora();

        int recibeBT=computadora.getBt();

        switch(recibeBT){
            case 0:
                posPC=7;
                break;
            case 1:
                posPC=8;
                break;
            case 2:
                posPC=9;
                break;
            case 3:
                posPC=10;
                break;
            case 4:
                posPC=11;
                break;
            case 5:
                posPC=12;
                break;
        }

        return posPC;
    }//termina  tiroPC

    private void ejecutarTiro(int posiPC){

        System.out.println("La computadora hara el tiro: " + posiPC);

        if(receptaculos[posiPC]==0){
            posiPC=7;

            if(receptaculos[7]==0){
                posiPC=8;

                if(receptaculos[8]==0){
                    posiPC=9;

                    if(receptaculos[9]==0){
                        posiPC=10;

                        if(receptaculos[10]==0){
                            posiPC=11;

                            if(receptaculos[11]==0){posiPC=12;}
                        }
                    }


                }

            }



        }//termina if

        int ppc = receptaculos[posiPC];
        System.out.println("ppc: " + ppc);
        receptaculos[posiPC]=0;
        //fichas[7].setText("0");
        fichas[posiPC].setText(Integer.toString(receptaculos[posiPC]));

        for(int i=0; i<ppc; i++){
            if(posiPC==13){posiPC=-1;}

            posiPC=posiPC+1;

            if(posiPC==6){posiPC=7;}

            receptaculos[posiPC]++;
            fichas[posiPC].setText(Integer.toString(receptaculos[posiPC]));
        }//termina el tiro

        if((posiPC>6)&&(posiPC<13)){
            int z=receptaculos[posiPC];
            if(z==1){
                System.out.println("roba" + posiPC);
                robar(posiPC);
            }
        }//termina if que busca si  va a robar o no

        if(posiPC==13){
            System.out.println("La computadora repite tiro");
            computadora objet = new computadora();
            objet.simularTiro(receptaculos);

            objet.imprimejugadas();

            objet.separaJugadas();

            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
        }

    }//termina funcion ejercutarTiro

    private void robar(int x){
        switch(x){
            case 7:
                if (receptaculos[5] != 0) {
                    receptaculos[13]=receptaculos[13]+receptaculos[5]+1;
                    receptaculos[7]=0;
                    receptaculos[5]=0;
                    fichas[13].setText(Integer.toString(receptaculos[13]));
                    fichas[7].setText(Integer.toString(receptaculos[7]));
                    fichas[5].setText(Integer.toString(receptaculos[5]));
                }
                break;
            case 8:
                if(receptaculos[4]==0){}else{
                    receptaculos[13]=receptaculos[13]+receptaculos[4]+1;
                    receptaculos[8]=0;
                    receptaculos[4]=0;
                    fichas[13].setText(Integer.toString(receptaculos[13]));
                    fichas[8].setText(Integer.toString(receptaculos[8]));
                    fichas[4].setText(Integer.toString(receptaculos[4]));
                }
                break;
            case 9:
                if(receptaculos[3]==0){}else{
                    receptaculos[13]=receptaculos[13]+receptaculos[3]+1;
                    receptaculos[9]=0;
                    receptaculos[3]=0;
                    fichas[13].setText(Integer.toString(receptaculos[13]));
                    fichas[9].setText(Integer.toString(receptaculos[9]));
                    fichas[3].setText(Integer.toString(receptaculos[3]));
                }
                break;
            case 10:
                if(receptaculos[2]==0){}else{
                    receptaculos[13]=receptaculos[13]+receptaculos[2]+1;
                    receptaculos[10]=0;
                    receptaculos[2]=0;
                    fichas[13].setText(Integer.toString(receptaculos[13]));
                    fichas[10].setText(Integer.toString(receptaculos[10]));
                    fichas[2].setText(Integer.toString(receptaculos[2]));
                }
                break;
            case 11:
                if(receptaculos[1]==0){}else{
                    receptaculos[13]=receptaculos[13]+receptaculos[1]+1;
                    receptaculos[11]=0;
                    receptaculos[1]=0;
                    fichas[13].setText(Integer.toString(receptaculos[13]));
                    fichas[11].setText(Integer.toString(receptaculos[11]));
                    fichas[1].setText(Integer.toString(receptaculos[1]));
                }
                break;
            case 12:
                if(receptaculos[0]==0){}else{
                    receptaculos[13]=receptaculos[13]+receptaculos[0]+1;
                    receptaculos[12]=0;
                    receptaculos[0]=0;
                    fichas[13].setText(Integer.toString(receptaculos[13]));
                    fichas[12].setText(Integer.toString(receptaculos[12]));
                    fichas[0].setText(Integer.toString(receptaculos[0]));
                }
                break;


        }//termina switch

    }//termina función robar

    public void ganador(){

        int sumaUno=0;
        for(int k=0; k<6; k++){
            sumaUno=sumaUno+receptaculos[k];
        }


        if(sumaUno==0){
            if(receptaculos[6]>receptaculos[13]){
                JOptionPane.showMessageDialog(null, "FELICIDADES!!!\n HAS GANADO");

                B1.setEnabled(false);
                B2.setEnabled(false);
                B3.setEnabled(false);
                B4.setEnabled(false);
                B5.setEnabled(false);
                B6.setEnabled(false);

            }else if(receptaculos[13]>receptaculos[6]){
                JOptionPane.showMessageDialog(null, "LA COMPUTADORA HA GANADO \n INTENTA OTRA VEZ");
                deshabilitarBotones();
            }

        }

        int sumaDos=0;
        for(int j=7; j<13; j++){
            sumaDos=sumaDos+receptaculos[j];
        }

        if(sumaDos==0){
            if(receptaculos[6]>receptaculos[13]){
                JOptionPane.showMessageDialog(null, "FELICIDADES!!!\n HAS GANADO");

                deshabilitarBotones();
                reiniciarJuego();

            }else if(receptaculos[13]>receptaculos[6]){
                JOptionPane.showMessageDialog(null, "LA COMPUTADORA HA GANADO \n INTENTA OTRA VEZ");
                deshabilitarBotones();
                reiniciarJuego();
            }

        }


        if(receptaculos[6]>24){
            JOptionPane.showMessageDialog(null, "FELICIDADES!!!\n HAS GANADO");
            deshabilitarBotones();
            reiniciarJuego();

        }else if(receptaculos[13]>24){
            JOptionPane.showMessageDialog(null, "LA COMPUTADORA HA GANADO \n INTENTA OTRA VEZ");
            deshabilitarBotones();
            reiniciarJuego();
        }
    }

    public void deshabilitarBotones(){
        B1.setEnabled(false);
        B2.setEnabled(false);
        B3.setEnabled(false);
        B4.setEnabled(false);
        B5.setEnabled(false);
        B6.setEnabled(false);
    }

    public void reiniciarJuego(){

        int choice = JOptionPane.showOptionDialog(null,"¿Quieres Jugar Otra vez?","Reincio de Juego",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,botones,"default");

        if(choice==1){
            System.out.println("Selecionaste reiniciar");
            B1.setEnabled(true);
            B2.setEnabled(true);
            B3.setEnabled(true);
            B4.setEnabled(true);
            B5.setEnabled(true);
            B6.setEnabled(true);

            for(int k=0; k<14;k++){
                receptaculos[k]=4;
                fichas[k].setText(Integer.toString(receptaculos[k]));
            }
        }else{

        }

    }//termina clase

    private void PrimerBoton(){
        int ultimaPosicion;
        int x = receptaculos[0];
        receptaculos[0] = 0;
        posicion=1;
        fichas[posicion-1].setText(Integer.toString(receptaculos[0]));

        for(int i=0; i<x; i++){
            if(posicion==13){
                posicion=0;
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }else{
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }
        }

        ultimaPosicion= posicion-1;

        if((ultimaPosicion<6)&&(receptaculos[ultimaPosicion]==1)){
            boolean noModificar=false;
            int plus=0;//variable para guardar las semillas de la canasta opuesta

            switch(ultimaPosicion){
                case 0:
                    if(receptaculos[12]==0){
                        noModificar=true;
                    }else{
                        receptaculos[0]=0;
                        plus=receptaculos[12];//guarda las semillas de la canasta opuesta
                        receptaculos[12]=0;//vacía la canasta opuesta
                        fichas[0].setText(prueba(0));
                        fichas[12].setText(prueba(12));
                    }
                    break;
                case 1:
                    if(receptaculos[11]==0){
                        noModificar=true;
                    }else{
                        receptaculos[1]=0;
                        plus=receptaculos[11];//guarda las semillas de la canasta opuesta
                        receptaculos[11]=0;//vacía la canasta opuesta
                        fichas[1].setText(prueba(1));
                        fichas[11].setText(prueba(11));
                    }
                    break;
                case 2:
                    if(receptaculos[10]==0){
                        noModificar=true;
                    }else{
                        receptaculos[2]=0;
                        plus=receptaculos[10];//guarda las semillas de la canasta opuesta
                        receptaculos[10]=0;//vacía la canasta opuesta
                        fichas[2].setText(prueba(2));
                        fichas[10].setText(prueba(10));
                    }
                    break;
                case 3:
                    if(receptaculos[9]==0){
                        noModificar=true;
                    }else{
                        receptaculos[3]=0;
                        plus=receptaculos[9];//guarda las semillas de la canasta opuesta
                        receptaculos[9]=0;//vacía la canasta opuesta
                        fichas[3].setText(prueba(3));
                        fichas[9].setText(prueba(9));
                    }
                    break;
                case 4:
                    if(receptaculos[8]==0){
                        noModificar=true;
                    }else{
                        receptaculos[4]=0;
                        plus=receptaculos[8];//guarda las semillas de la canasta opuesta
                        receptaculos[8]=0;//vacía la canasta opuesta
                        fichas[4].setText(prueba(4));
                        fichas[8].setText(prueba(8));
                    }
                    break;
                case 5:
                    if(receptaculos[7]==0){
                        noModificar=true;
                    }else{
                        receptaculos[5]=0;
                        plus=receptaculos[7];//guarda las semillas de la canasta opuesta
                        receptaculos[7]=0;//vacía la canasta opuesta
                        fichas[5].setText(prueba(5));
                        fichas[7].setText(prueba(7));
                    }
                    break;
            }

            if(noModificar){
            }else{
                receptaculos[6]= receptaculos[6]+ 1+ plus;
                fichas[6].setText(prueba(6));
            }
        }//termina el if que checa si en la ultima posicion estaba vacia la canasta

        for(int j=0; j<14; j++){
            System.out.print(" |"+receptaculos[j]);
        }

        System.out.println("");
        System.out.println("**************************************");

        if(ultimaPosicion==6){
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");
            computadora objet = new computadora();
            objet.simularTiro(receptaculos);
            objet.imprimejugadas();
            objet.separaJugadas();
            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
            ganador();
        }
    }//Termina boton 1

    public void segundoBoton(){
        int ultimaPosicion;
        int x = receptaculos[1];
        receptaculos[1] = 0;
        posicion=2;
        fichas[posicion-1].setText(Integer.toString(receptaculos[1]));

        for(int i=0; i<x; i++){
            if(posicion==13){
                posicion=0;
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }else{
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }
        }

        System.out.println("**************************************");
        System.out.println(posicion);
        System.out.println(receptaculos[posicion]);

        ultimaPosicion= posicion-1;

        if((ultimaPosicion<6)&&(receptaculos[ultimaPosicion]==1))
        {
            boolean noModificar=false;
            int plus=0;//variable para guardar las semillas de la canasta opuesta
            switch(ultimaPosicion)
            {
                case 0:
                    if(receptaculos[12]==0){noModificar=true;}else{
                        receptaculos[0]=0;
                        plus=receptaculos[12];//guarda las semillas de la canasta opuesta
                        receptaculos[12]=0;//vacía la canasta opuesta
                        fichas[0].setText(prueba(0));
                        fichas[12].setText(prueba(12));
                    }
                    break;
                case 1:
                    if(receptaculos[11]==0){noModificar=true;}else{
                        receptaculos[1]=0;
                        plus=receptaculos[11];//guarda las semillas de la canasta opuesta
                        receptaculos[11]=0;//vacía la canasta opuesta
                        fichas[1].setText(prueba(1));
                        fichas[11].setText(prueba(11));
                    }
                    break;
                case 2:
                    if(receptaculos[10]==0){noModificar=true;}else{
                        receptaculos[2]=0;
                        plus=receptaculos[10];//guarda las semillas de la canasta opuesta
                        receptaculos[10]=0;//vacía la canasta opuesta
                        fichas[2].setText(prueba(2));
                        fichas[10].setText(prueba(10));
                    }
                    break;
                case 3:
                    if(receptaculos[9]==0){noModificar=true;}else{
                        receptaculos[3]=0;
                        plus=receptaculos[9];//guarda las semillas de la canasta opuesta
                        receptaculos[9]=0;//vacía la canasta opuesta
                        fichas[3].setText(prueba(3));
                        fichas[9].setText(prueba(9));
                    }
                    break;
                case 4:
                    if(receptaculos[8]==0){noModificar=true;}else{
                        receptaculos[4]=0;
                        plus=receptaculos[8];//guarda las semillas de la canasta opuesta
                        receptaculos[8]=0;//vacía la canasta opuesta
                        fichas[4].setText(prueba(4));
                        fichas[8].setText(prueba(8));
                    }
                    break;
                case 5:
                    if(receptaculos[7]==0){noModificar=true;}else{
                        receptaculos[5]=0;
                        plus=receptaculos[7];//guarda las semillas de la canasta opuesta
                        receptaculos[7]=0;//vacía la canasta opuesta
                        fichas[5].setText(prueba(5));
                        fichas[7].setText(prueba(7));
                    }
                    break;
            }
            if(noModificar){}else{
                receptaculos[6]= receptaculos[6]+ 1+ plus;
                fichas[6].setText(prueba(6));
            }

        }//termina el if que checa si en la ultima posicion estaba vacia la canasta

        for(int j=0; j<14; j++)
            System.out.println(" indice receptaculos = "+receptaculos[j]);

        System.out.println("**************************************");
        System.out.println(ultimaPosicion);

        if(ultimaPosicion==6)
        {
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");
            computadora objet = new computadora();
            objet.simularTiro(receptaculos);

            objet.imprimejugadas();

            objet.separaJugadas();

            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
        }

        ganador();
    }//Termina boton 2

    public void tercerBoton(){
        int ultimaPosicion;
        int x = receptaculos[2];
        receptaculos[2] = 0;
        posicion=3;
        fichas[posicion-1].setText(Integer.toString(receptaculos[2]));

        for(int i=0; i<x; i++)
        {
            if(posicion==13){
                posicion=0;
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }else{
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }
        }

        System.out.println("**************************************");
        System.out.println(posicion);
        System.out.println(receptaculos[posicion]);

        ultimaPosicion= posicion-1;

        if((ultimaPosicion<6)&&(receptaculos[ultimaPosicion]==1))
        {
            boolean noModificar=false;
            int plus=0;//variable para guardar las semillas de la canasta opuesta
            switch(ultimaPosicion)
            {
                case 0:
                    if(receptaculos[12]==0){noModificar=true;}else{
                        receptaculos[0]=0;
                        plus=receptaculos[12];//guarda las semillas de la canasta opuesta
                        receptaculos[12]=0;//vacía la canasta opuesta
                        fichas[0].setText(prueba(0));
                        fichas[12].setText(prueba(12));
                    }
                    break;
                case 1:
                    if(receptaculos[11]==0){noModificar=true;}else{
                        receptaculos[1]=0;
                        plus=receptaculos[11];//guarda las semillas de la canasta opuesta
                        receptaculos[11]=0;//vacía la canasta opuesta
                        fichas[1].setText(prueba(1));
                        fichas[11].setText(prueba(11));
                    }
                    break;
                case 2:
                    if(receptaculos[10]==0){noModificar=true;}else{
                        receptaculos[2]=0;
                        plus=receptaculos[10];//guarda las semillas de la canasta opuesta
                        receptaculos[10]=0;//vacía la canasta opuesta
                        fichas[2].setText(prueba(2));
                        fichas[10].setText(prueba(10));
                    }
                    break;
                case 3:
                    if(receptaculos[9]==0){noModificar=true;}else{
                        receptaculos[3]=0;
                        plus=receptaculos[9];//guarda las semillas de la canasta opuesta
                        receptaculos[9]=0;//vacía la canasta opuesta
                        fichas[3].setText(prueba(3));
                        fichas[9].setText(prueba(9));
                    }
                    break;
                case 4:
                    if(receptaculos[8]==0){noModificar=true;}else{
                        receptaculos[4]=0;
                        plus=receptaculos[8];//guarda las semillas de la canasta opuesta
                        receptaculos[8]=0;//vacía la canasta opuesta
                        fichas[4].setText(prueba(4));
                        fichas[8].setText(prueba(8));
                    }
                    break;
                case 5:
                    if(receptaculos[7]==0){noModificar=true;}else{
                        receptaculos[5]=0;
                        plus=receptaculos[7];//guarda las semillas de la canasta opuesta
                        receptaculos[7]=0;//vacía la canasta opuesta
                        fichas[5].setText(prueba(5));
                        fichas[7].setText(prueba(7));
                    }
                    break;
            }
            if(noModificar){}else{

                receptaculos[6]= receptaculos[6]+ 1+ plus;
                fichas[6].setText(prueba(6));
            }

        }//termina el if que checa si en la ultima posicion estaba vacia la canasta

        for(int j=0; j<14; j++)
            System.out.println(" indice receptaculos = "+receptaculos[j]);

        System.out.println("**************************************");
        System.out.println(ultimaPosicion);

        if(ultimaPosicion==6)
        {
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");
            computadora objet = new computadora();
            objet.simularTiro(receptaculos);

            objet.imprimejugadas();

            objet.separaJugadas();

            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
        }

        ganador();
    }//Termina boton 3

    public void cuartoBoton(){
        int ultimaPosicion;
        int x = receptaculos[3];
        receptaculos[3] = 0;
        posicion=4;
        fichas[posicion-1].setText(Integer.toString(receptaculos[3]));

        for(int i=0; i<x; i++)
        {
            if(posicion==13){
                posicion=0;
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }else{
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }
        }

        System.out.println("**************************************");
        System.out.println(posicion);
        System.out.println(receptaculos[posicion]);

        ultimaPosicion= posicion-1;

        if((ultimaPosicion<6)&&(receptaculos[ultimaPosicion]==1))
        {
            boolean noModificar=false;
            int plus=0;//variable para guardar las semillas de la canasta opuesta
            switch(ultimaPosicion)
            {
                case 0:
                    if(receptaculos[12]==0){noModificar=true;}else{
                        receptaculos[0]=0;
                        plus=receptaculos[12];//guarda las semillas de la canasta opuesta
                        receptaculos[12]=0;//vacía la canasta opuesta
                        fichas[0].setText(prueba(0));
                        fichas[12].setText(prueba(12));
                    }
                    break;
                case 1:
                    if(receptaculos[11]==0){noModificar=true;}else{
                        receptaculos[1]=0;
                        plus=receptaculos[11];//guarda las semillas de la canasta opuesta
                        receptaculos[11]=0;//vacía la canasta opuesta
                        fichas[1].setText(prueba(1));
                        fichas[11].setText(prueba(11));
                    }
                    break;
                case 2:
                    if(receptaculos[10]==0){noModificar=true;}else{
                        receptaculos[2]=0;
                        plus=receptaculos[10];//guarda las semillas de la canasta opuesta
                        receptaculos[10]=0;//vacía la canasta opuesta
                        fichas[2].setText(prueba(2));
                        fichas[10].setText(prueba(10));
                    }
                    break;
                case 3:
                    if(receptaculos[9]==0){noModificar=true;}else{
                        receptaculos[3]=0;
                        plus=receptaculos[9];//guarda las semillas de la canasta opuesta
                        receptaculos[9]=0;//vacía la canasta opuesta
                        fichas[3].setText(prueba(3));
                        fichas[9].setText(prueba(9));
                    }
                    break;
                case 4:
                    if(receptaculos[8]==0){noModificar=true;}else{
                        receptaculos[4]=0;
                        plus=receptaculos[8];//guarda las semillas de la canasta opuesta
                        receptaculos[8]=0;//vacía la canasta opuesta
                        fichas[4].setText(prueba(4));
                        fichas[8].setText(prueba(8));
                    }
                    break;
                case 5:
                    if(receptaculos[7]==0){noModificar=true;}else{
                        receptaculos[5]=0;
                        plus=receptaculos[7];//guarda las semillas de la canasta opuesta
                        receptaculos[7]=0;//vacía la canasta opuesta
                        fichas[5].setText(prueba(5));
                        fichas[7].setText(prueba(7));
                    }
                    break;
            }
            if(noModificar){}else{

                receptaculos[6]= receptaculos[6]+ 1+ plus;
                fichas[6].setText(prueba(6));
            }


        }//termina el if que checa si en la ultima posicion estaba vacia la canasta

        for(int j=0; j<14; j++)
            System.out.println(" indice receptaculos = "+receptaculos[j]);

        System.out.println("**************************************");
        System.out.println(ultimaPosicion);

        if(ultimaPosicion==6)
        {
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");
            computadora objet = new computadora();
            objet.simularTiro(receptaculos);

            objet.imprimejugadas();

            objet.separaJugadas();

            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
        }

        ganador();
    }//Termina boton 4

    public void quintoBoton(){
        int ultimaPosicion;
        int x = receptaculos[4];
        receptaculos[4] = 0;
        posicion=5;
        fichas[posicion-1].setText(Integer.toString(receptaculos[4]));

        for(int i=0; i<x; i++)
        {
            if(posicion==13){
                posicion=0;
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }else{
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }
        }

        System.out.println("**************************************");
        System.out.println(posicion);
        System.out.println(receptaculos[posicion]);

        ultimaPosicion= posicion-1;
        if(ultimaPosicion==6)
        {
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");
        }

        if((ultimaPosicion<6)&&(receptaculos[ultimaPosicion]==1))
        {
            boolean noModificar=false;
            int plus=0;//variable para guardar las semillas de la canasta opuesta
            switch(ultimaPosicion)
            {
                case 0:
                    if(receptaculos[12]==0){noModificar=true;}else{
                        receptaculos[0]=0;
                        plus=receptaculos[12];//guarda las semillas de la canasta opuesta
                        receptaculos[12]=0;//vacía la canasta opuesta
                        fichas[0].setText(prueba(0));
                        fichas[12].setText(prueba(12));
                    }
                    break;
                case 1:
                    if(receptaculos[11]==0){noModificar=true;}else{
                        receptaculos[1]=0;
                        plus=receptaculos[11];//guarda las semillas de la canasta opuesta
                        receptaculos[11]=0;//vacía la canasta opuesta
                        fichas[1].setText(prueba(1));
                        fichas[11].setText(prueba(11));
                    }
                    break;
                case 2:
                    if(receptaculos[10]==0){noModificar=true;}else{
                        receptaculos[2]=0;
                        plus=receptaculos[10];//guarda las semillas de la canasta opuesta
                        receptaculos[10]=0;//vacía la canasta opuesta
                        fichas[2].setText(prueba(2));
                        fichas[10].setText(prueba(10));
                    }
                    break;
                case 3:
                    if(receptaculos[9]==0){noModificar=true;}else{
                        receptaculos[3]=0;
                        plus=receptaculos[9];//guarda las semillas de la canasta opuesta
                        receptaculos[9]=0;//vacía la canasta opuesta
                        fichas[3].setText(prueba(3));
                        fichas[9].setText(prueba(9));
                    }
                    break;
                case 4:
                    if(receptaculos[8]==0){noModificar=true;}else{
                        receptaculos[4]=0;
                        plus=receptaculos[8];//guarda las semillas de la canasta opuesta
                        receptaculos[8]=0;//vacía la canasta opuesta
                        fichas[4].setText(prueba(4));
                        fichas[8].setText(prueba(8));
                    }
                    break;
                case 5:
                    if(receptaculos[7]==0){noModificar=true;}else{
                        receptaculos[5]=0;
                        plus=receptaculos[7];//guarda las semillas de la canasta opuesta
                        receptaculos[7]=0;//vacía la canasta opuesta
                        fichas[5].setText(prueba(5));
                        fichas[7].setText(prueba(7));
                    }
                    break;
            }
            if(noModificar){}else{

                receptaculos[6]= receptaculos[6]+ 1+ plus;
                fichas[6].setText(prueba(6));
            }

        }//termina el if que checa si en la ultima posicion estaba vacia la canasta

        for(int j=0; j<14; j++)
            System.out.println(" indice receptaculos = "+receptaculos[j]);

        System.out.println("**************************************");
        System.out.println(ultimaPosicion);

        if(ultimaPosicion==6)
        {
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");

            computadora objet = new computadora();
            objet.simularTiro(receptaculos);

            objet.imprimejugadas();

            objet.separaJugadas();

            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
        }


        ganador();
    }//Termina boton 5

    public void sextoBoton(){
        int ultimaPosicion;
        int x = receptaculos[5];
        receptaculos[5] = 0;
        posicion=6;
        fichas[posicion-1].setText(Integer.toString(receptaculos[5]));

        for(int i=0; i<x; i++)
        {
            if(posicion==13){
                posicion=0;
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }else{
                receptaculos[posicion]++;
                fichas[posicion].setText(prueba(posicion));
                posicion++;
            }
        }

        System.out.println("**************************************");
        System.out.println(posicion);
        System.out.println(receptaculos[posicion]);

        ultimaPosicion= posicion-1;

        if((ultimaPosicion<6)&&(receptaculos[ultimaPosicion]==1))
        {
            boolean noModificar=false;
            int plus=0;//variable para guardar las semillas de la canasta opuesta
            switch(ultimaPosicion)
            {
                case 0:
                    if(receptaculos[12]==0){noModificar=true;}else{
                        receptaculos[0]=0;
                        plus=receptaculos[12];//guarda las semillas de la canasta opuesta
                        receptaculos[12]=0;//vacía la canasta opuesta
                        fichas[0].setText(prueba(0));
                        fichas[12].setText(prueba(12));
                    }
                    break;
                case 1:
                    if(receptaculos[11]==0){noModificar=true;}else{
                        receptaculos[1]=0;
                        plus=receptaculos[11];//guarda las semillas de la canasta opuesta
                        receptaculos[11]=0;//vacía la canasta opuesta
                        fichas[1].setText(prueba(1));
                        fichas[11].setText(prueba(11));
                    }
                    break;
                case 2:
                    if(receptaculos[10]==0){noModificar=true;}else{
                        receptaculos[2]=0;
                        plus=receptaculos[10];//guarda las semillas de la canasta opuesta
                        receptaculos[10]=0;//vacía la canasta opuesta
                        fichas[2].setText(prueba(2));
                        fichas[10].setText(prueba(10));
                    }
                    break;
                case 3:
                    if(receptaculos[9]==0){noModificar=true;}else{
                        receptaculos[3]=0;
                        plus=receptaculos[9];//guarda las semillas de la canasta opuesta
                        receptaculos[9]=0;//vacía la canasta opuesta
                        fichas[3].setText(prueba(3));
                        fichas[9].setText(prueba(9));
                    }
                    break;
                case 4:
                    if(receptaculos[8]==0){noModificar=true;}else{
                        receptaculos[4]=0;
                        plus=receptaculos[8];//guarda las semillas de la canasta opuesta
                        receptaculos[8]=0;//vacía la canasta opuesta
                        fichas[4].setText(prueba(4));
                        fichas[8].setText(prueba(8));
                    }
                    break;
                case 5:
                    if(receptaculos[7]==0){noModificar=true;}else{
                        receptaculos[5]=0;
                        plus=receptaculos[7];//guarda las semillas de la canasta opuesta
                        receptaculos[7]=0;//vacía la canasta opuesta
                        fichas[5].setText(prueba(5));
                        fichas[7].setText(prueba(7));
                    }
                    break;
            }
            if(noModificar){}else{

                receptaculos[6]= receptaculos[6]+ 1+ plus;
                fichas[6].setText(prueba(6));
            }

        }//termina el if que checa si en la ultima posicion estaba vacia la canasta

        for(int j=0; j<14; j++)
            System.out.println(" indice receptaculos = "+receptaculos[j]);



        System.out.println("**************************************");
        System.out.println(ultimaPosicion);

        if(ultimaPosicion==6)
        {
            JOptionPane.showMessageDialog(null, "Tienes otro tiro");
        }else{
            JOptionPane.showMessageDialog(null, "Turno de la computadora");

            computadora objet = new computadora();
            objet.simularTiro(receptaculos);

            objet.imprimejugadas();

            objet.separaJugadas();

            objet.imprimeArreglos();
            tiroPc();
            ejecutarTiro(tiroPc());
        }


        ganador();
    }//Termina boton 6

    public void renombrarBotones(){
        B1.setText(Integer.toString(receptaculos[0]));
        B2.setText(Integer.toString(receptaculos[1]));
        B3.setText(Integer.toString(receptaculos[2]));
        B4.setText(Integer.toString(receptaculos[3]));
        B5.setText(Integer.toString(receptaculos[4]));
        B6.setText(Integer.toString(receptaculos[5]));
    }

    class oyenteMenu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == B1) {
                PrimerBoton();
                renombrarBotones();
            }else if (e.getSource() == B2){
                segundoBoton();
                renombrarBotones();
            }else if (e.getSource() == B3){
                tercerBoton();
                renombrarBotones();
            }else if (e.getSource() == B4){
                cuartoBoton();
                renombrarBotones();
            }else if (e.getSource() == B5){
                quintoBoton();
                renombrarBotones();
            }else if (e.getSource() == B6){
                sextoBoton();
                renombrarBotones();
            }
        }

    }
}