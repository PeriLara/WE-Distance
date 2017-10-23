import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Echo {
    
    
    /**
     * Imprime sur la sortie standard les arguments du programme.
     * Si aucun argument n'est donné, on lit à la place l'entrée standard.
     */
    public static void main(String[] args){
        
        if (args.length > 0){
            for (String s : args){
                System.out.print(s);
                System.out.print(" ");
            }
            System.out.println();
        }else{
            
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input;
                input = br.readLine();
                while(input != null){
                    System.out.println(input);
                    input = br.readLine();
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
}
