package ex2.jogodavelha;
import java.util.Scanner;
import java.lang.Math;
import java.util.*;

public class Jogo {
    private String s1;
    private String s2;
    private String[][] mat = new String[3][3];

    public Jogo(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                mat[i][j] = " ";
    }

    private void imprime(){
        int b=0;
        for(int a=0;a<3;a++){
            System.out.println(mat[a][b] + " | " + mat[a][b+1] + " | " + mat[a][b+2] );
            if(a<2)
            System.out.println("- - - - -");
        }
    }

    private boolean modifica(String jogada, String simbolo){
        if(jogada.length()==5){
            try{
                int a = Integer.parseInt(jogada.substring(1,2));
                int b = Integer.parseInt(jogada.substring(3,4));
                if(!(jogada.substring(2,3).equals(",") &&
                jogada.substring(0,1).equals("(") &&
                jogada.substring(4).equals(")")))
                    return false;
                if(a>=0 && a<=2 && b>=0 && b<=2){
                    if(!(mat[a][b].equals(s1) || mat[a][b].equals(s2))){
                        mat[a][b] = simbolo;
                        return true;
                        }
                        else
                            return false;
                }
                else 
                        return false;
            }catch(Exception e){
                return false;
            }
       }
       else
            return false;
    }

    private int vitoria(){
        if(//linhas
            (mat[0][0] == s2 && mat[0][1] == s2 && mat[0][2] == s2) ||

            (mat[1][0] == s2 && mat[1][1] == s2 && mat[1][2] == s2) ||
            
            (mat[2][0] == s2 && mat[2][1] == s2 && mat[2][2] == s2) ||
            
            
            //colunas
            (mat[0][0] == s2 && mat[1][0] == s2 && mat[2][0] == s2) ||

            (mat[0][1] == s2 && mat[1][1] == s2 && mat[2][1] == s2) ||
            
            (mat[0][2] == s2 && mat[1][2] == s2 && mat[2][2] == s2)||
            
            
            //diagonais
            (mat[0][0] == s2 && mat[1][1] == s2 && mat[2][2] == s2) ||
        
            (mat[2][0] == s2 && mat[1][1] == s2 && mat[0][2] == s2) )
                return 1;
            
        
        else if(//linhas
            (mat[0][0] == s1 && mat[0][1] == s1 && mat[0][2] == s1) ||

            (mat[1][0] == s1 && mat[1][1] == s1 && mat[1][2] == s1) ||

            (mat[2][0] == s1 && mat[2][1] == s1 && mat[2][2] == s1) ||


            //colunas
            (mat[0][0] == s1 && mat[1][0] == s1 && mat[2][0] == s1) ||
           
            (mat[0][1] == s1 && mat[1][1] == s1 && mat[2][1] == s1) ||

            (mat[0][2] == s1 && mat[1][2] == s1 && mat[2][2] == s1) ||


            //diagonais
            (mat[0][0] == s1 && mat[1][1] == s1 && mat[2][2] == s1) ||

            (mat[2][0] == s1 && mat[1][1] == s1 && mat[0][2] == s1)
        )
            return 2;
        
        else
            return 0;
    }

    private String jogada(String jogador, String simbolo){
        String jogada;
        if(jogador.equals("Bot")){
            double c = Math.random() * 3;
            double d = Math.random() * 3;
            this.s2 = "O";
            jogada = "(" + (int)c + "," + (int)d + ")";
            while(!modifica(jogada,s2)){
                c = Math.random() * 3;
                d = Math.random() * 3;
                jogada = "(" + (int)c + "," + (int)d + ")";
            }
            System.out.println("Vez de Bot:");
            System.out.println("O Bot jogou na posição " + jogada);
        }
        else{
            Scanner teclado = new Scanner(System.in);
            System.out.println("Vez de " + jogador + ":" + "\n" + "Digite a posição que deseja jogar \"(linha,coluna)\":");
            jogada = teclado.next();
            while(!modifica(jogada,simbolo)){
                System.out.println("Vez de " + jogador + ":" + "\n" + "Digite a posição que deseja jogar \"(linha,coluna)\":");
                jogada = teclado.next();
            }
        }
        imprime(); 
        return jogada;       
    }

    public void jogar(){
        List<String> jogadas = new ArrayList<> ();
        int vitoria = 0;
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" +
                           "~~~~ Seja bem vindo ao jogo da velha! ~~~~" + "\n" +
                           "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n");
        System.out.print("Se você deseja jogar contra outra pessoa digite 1, agora, se você deseja jogar contra um bot digite outro valor:");
        Scanner teclado = new Scanner(System.in);
        String modo = teclado.next();

        //contra outra pessoa
        if(modo.equals("1")){
            System.out.println("Jogador 1 digite o símbolo que deseja utilizar:");
            String s1 = teclado.next();
            while(s1.length()!=1){
                System.out.println("Jogador 1 o símbolo deve possuir apenas um caracter, digite seu símbolo novamente:");
                s1 = teclado.next();
            }
            this.s1 = s1;

            System.out.println("Jogador 2 digite o símbolo que deseja utilizar (este deve ser diferente do utilizado pelo Jogador 1):");
            String s2 = teclado.next();
            while(s2.equals(s1)){
                System.out.println("Jogador 2 digite o símbolo que deseja utilizar (este deve ser diferente do utilizado pelo Jogador 1):");
                s2 = teclado.next();
            }
            while(s2.length()!=1){
                System.out.println("Jogador 2 o símbolo deve possuir apenas um caracter, digite seu símbolo novamente:");
                s2 = teclado.next();
            }
            this.s2 = s2;
            
            for(int i=0;i<5;i++){
                jogadas.add(jogada("Jogador 1",s1));
                if(i>1){
                    vitoria = vitoria();
                    if(vitoria == 2){
                        System.out.println("Parabéns Jogador 1, você venceu!!");
                        break;
                    }
                }
                if(i==4){
                    System.out.println("Vishh, deu velha!!");
                    break;
                }
                jogadas.add(jogada("Jogador 2",s2));
                if(i>1){
                    vitoria = vitoria();
                    if(vitoria == 1){
                        System.out.println("Parabéns Jogador 2, você venceu!!");
                        break;
                    }
                }
            }
            
        }
        //contra o Bot
        else{
            System.out.println("Jogador 1 digite o símbolo que deseja utilizar:");
            String s1 = teclado.next();
            while(s1.length()!=1){
                System.out.println("Jogador 1 o símbolo deve possuir apenas um caracter, digite seu símbolo novamente:");
                s1 = teclado.next();
            }
            this.s1 = s1;
            if(s1.equals("O"))
                this.s2 = "X";
            else
                this.s2 = "O";
            System.out.println("O bot escolheu o símbolo \"O\"");
            for(int i=0;i<5;i++){
                jogadas.add(jogada("Jogador 1",s1));
                if(i>1){
                    vitoria = vitoria();
                    if(vitoria == 2){
                        System.out.println("Parabéns Jogador 1, você venceu!!");
                        break;
                    }
                }
                if(i==4){
                    System.out.println("Vishh, deu velha!!");
                    break;
                }
                jogadas.add(jogada("Bot",s2));
                if(i>1){
                    vitoria = vitoria();
                    if(vitoria == 1){
                        System.out.println("Você perdeu para o Bot!!");
                        break;
                    }
                }
            }
        }

        System.out.println("As jogadas realizadas foram:");
        for (String jogada : jogadas){
            System.out.println(jogada);
        }


    }
}
