
package Mancala;

/**
 *
 * @authors Jonathan Nicio
 */


class computadora {
    private int indice=0;

    private static int[] jugadas ={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static int[] j1 = new int[6];
    private static int[] j2 = new int[6];
    private static int[] j3 = new int[6];
    private static int[] j4 = new int[6];
    private static int[] j5 = new int[6];
    private static int[] j6 = new int[6];
    private static int[] opciones = new int[6];

    private static int bt=0;

    private static int[] boton = new int[6];

    private int[] tableroPSimular = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    void simularTiro(int tablero[]){
        int up=0;

        for(int k=0; k<6;k++){ //Empieza for para simular los tiros
            System.arraycopy(tablero, 0, tableroPSimular, 0, tablero.length);

            int pos=8+k;
            System.out.println("posicion inicial " +(pos));
            int r=tableroPSimular[7+k];
            tableroPSimular[7+k]=0;

            for(int j=0; j<r; j++)
            {
                if(pos==6)
                {
                    System.out.println("llego a las posicion 6 ");
                    pos=7;
                    tableroPSimular[pos]++;
                    pos++;
                }else{//empieza else
                    tableroPSimular[pos]++;
                    pos++;
                    if(pos==14){
                        pos=0;
                    }
                }//termina else

            }

            System.out.println("posicion antes de terminar el for: " + pos);

            up=pos-1;

            if((up>6)&&(up<13)&&((tableroPSimular[up]==1)))
            {
                boolean noModificar=false;
                int plus=0;//variable para guardar las semillas de la canasta opuesta
                switch(up)
                {

                    case 7:
                        if(tableroPSimular[7]==0){noModificar=true;}else{
                            tableroPSimular[7]=0;
                            plus=tableroPSimular[5];//guarda las semillas de la canasta opuesta
                            tableroPSimular[5]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 8:
                        if(tableroPSimular[4]==0){noModificar=true;}else{
                            tableroPSimular[8]=0;
                            plus=tableroPSimular[4];//guarda las semillas de la canasta opuesta
                            tableroPSimular[4]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 9:
                        if(tableroPSimular[3]==0){noModificar=true;}else{
                            tableroPSimular[9]=0;
                            plus=tableroPSimular[3];//guarda las semillas de la canasta opuesta
                            tableroPSimular[3]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 10:
                        if(tableroPSimular[2]==0){noModificar=true;}else{
                            tableroPSimular[10]=0;
                            plus=tableroPSimular[2];//guarda las semillas de la canasta opuesta
                            tableroPSimular[2]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 11:
                        if(tableroPSimular[1]==0){noModificar=true;}else{
                            tableroPSimular[11]=0;
                            plus=tableroPSimular[1];//guarda las semillas de la canasta opuesta
                            tableroPSimular[1]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 12:
                        if(tableroPSimular[0]==0){noModificar=true;}else{
                            tableroPSimular[12]=0;
                            plus=tableroPSimular[0];//guarda las semillas de la canasta opuesta
                            tableroPSimular[0]=0;//vacía la canasta opuesta
                        }
                        break;
                }

                if (!noModificar) {
                    tableroPSimular[13]= tableroPSimular[13]+ 1 + plus;
                }
            }

            for(int i=0; i<tablero.length;i++){
                System.out.print(" | "+ tableroPSimular[i]);
            }System.out.println("");


            if(up==-1)
            {
                System.out.println("La computadora tiene otro tiro");
            }else{
                System.out.println("Turno del jugador");
                simularJugador(tableroPSimular);
            }

        }//termina for para simular los tiros



    }

    private void simularJugador(int tableroDos[]){

        int[] tableroParaAnalizar ={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int pos2=0;
        int y=0;
        //empieza a simualr tiros del jugador
        for(int j=0; j<6; j++){//empieza el for para simular tiros

            System.arraycopy(tableroDos, 0, tableroParaAnalizar, 0, tableroDos.length);

            pos2=1+j;

            y = tableroParaAnalizar[pos2-1];
            tableroParaAnalizar[pos2-1]=0;

            for(int k=0; k<y; k++)
            {
                if(pos2==13){
                    pos2=0;
                    tableroParaAnalizar[pos2]++;
                    pos2++;
                }else{
                    tableroParaAnalizar[pos2]++;
                    pos2++;
                }
            }

            int uPS= pos2-1;


            if((uPS<6)&&(tableroParaAnalizar[uPS]==1))
            {
                boolean noModificar=false;
                int plus3=0;//variable para guardar las semillas de la canasta opuesta
                switch(uPS)
                {
                    case 0:
                        if(tableroParaAnalizar[12]==0){noModificar=true;}else{
                            tableroParaAnalizar[0]=0;
                            plus3=tableroParaAnalizar[12];//guarda las semillas de la canasta opuesta
                            tableroParaAnalizar[12]=0;//vacía la canasta opuesta

                        }

                        break;
                    case 1:
                        if(tableroParaAnalizar[11]==0){noModificar=true;}else{
                            tableroParaAnalizar[1]=0;
                            plus3=tableroParaAnalizar[11];//guarda las semillas de la canasta opuesta
                            tableroParaAnalizar[11]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 2:
                        if(tableroParaAnalizar[10]==0){noModificar=true;}else{
                            tableroParaAnalizar[2]=0;
                            plus3=tableroParaAnalizar[10];//guarda las semillas de la canasta opuesta
                            tableroParaAnalizar[10]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 3:
                        if(tableroParaAnalizar[9]==0){noModificar=true;}else{
                            tableroParaAnalizar[3]=0;
                            plus3=tableroParaAnalizar[9];//guarda las semillas de la canasta opuesta
                            tableroParaAnalizar[9]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 4:
                        if(tableroParaAnalizar[8]==0){noModificar=true;}else{
                            tableroParaAnalizar[4]=0;
                            plus3=tableroParaAnalizar[8];//guarda las semillas de la canasta opuesta
                            tableroParaAnalizar[8]=0;//vacía la canasta opuesta
                        }
                        break;
                    case 5:
                        if(tableroParaAnalizar[7]==0){noModificar=true;

                        }else{
                            tableroParaAnalizar[5]=0;
                            plus3=tableroParaAnalizar[7];//guarda las semillas de la canasta opuesta
                            tableroParaAnalizar[7]=0;//vacía la canasta opuesta
                            //System.out.println("plus3: " + plus3);
                        }
                        break;
                }
                if (!noModificar) {
                    tableroParaAnalizar[6]= tableroParaAnalizar[6]+ 1+ plus3;
                }

            }

            for(int i=0; i<14; i++){ System.out.print("|" + tableroParaAnalizar[i]);

            }
            System.out.println(" ");
            System.out.println("Puntaje de la jugada = "+(tableroParaAnalizar[13]-tableroParaAnalizar[6]));
            System.out.println("indice = " + indice);
            indice++;

            if(indice==35){
                indice=0;
            }
            jugadas[indice]=(tableroParaAnalizar[13]-tableroParaAnalizar[6]);



        }//termina el for que simula los tiros del jugador


    }

    void imprimejugadas(){

        for(int f=0; f<36; f++)
            System.out.println("jugadas = " + jugadas[f]);
    }


    void separaJugadas(){

        int t = 0;

        System.arraycopy(jugadas, 0, j1, 0, 6);

        for(int i=6; i<12; i++){

            j2[t]=jugadas[i];
            t++;
        }
        t=0;
        for(int i=12; i<18; i++){

            j3[t]=jugadas[i];
            t++;
        }
        t=0;
        for(int i=18; i<24; i++){

            j4[t]=jugadas[i];
            t++;
        }
        t=0;
        for(int i=24; i<30; i++){

            j5[t]=jugadas[i];
            t++;
        }
        t=0;
        for(int i=30; i<35; i++){

            j6[t]=jugadas[i];
            t++;
        }

    }

    private int minimax(int lista[], int opciond){
        int menor=lista[0];


        for(int i=0; i<6; i++){
            if (lista[i] < menor) {
                menor=lista[i];
                int opcion = 0;
                opciones[opcion]=i;
            }
        }
        System.out.println("el numero menor es: " + menor);

        return menor;

    }

    private int maximax(int lista[]){
        int mayor=lista[0];
        //int bt=0;
        for(int i=0; i<6; i++){

            if(lista[i]>mayor){
                mayor=lista[i];
                bt=i;

            }
        }
        System.out.println("el numero menor es: " + mayor);
        System.out.println("el boton es el: " + bt);
        return mayor;

    }



    void imprimeArreglos(){


        boton[0]=minimax(j1,0);
        boton[1]=minimax(j2,1);
        boton[2]=minimax(j3,2);
        boton[3]=minimax(j4,3);
        boton[4]=minimax(j5,4);
        boton[5]=minimax(j6,5);


        System. out.println("La jugada a elegir es: " + maximax(boton));

        for(int i=0; i<6; i++){
            System. out.println(opciones[i]);
        }


    }



    //la funcion de tiro de la computadora debe recibir como parametro a la funcion
    //maximax que retorna la variable bt para que eliga en el switch
    //el tiro que debe hacer la computadora
    //hacer un switch que reciba el boton a elegir (bt) para que la computadora haga un tiro
    //el valor de bt se pasa a otra variable estatica
    /*

    swtich(bt)

    case 0
        computiro1
        break
    case 1
        computiro2
        break

    etcetera

    */

    static int getBt() {
        return bt;
    }


}
