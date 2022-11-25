package pc.mortalkombat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ManejadorArchivos {

    ManejadorArchivos(){

    }

    public String leer(String ruta){
        try{
            File archivo = new File(ruta);
            Scanner lector = new Scanner(archivo);
            StringBuilder data = new StringBuilder();

            while(lector.hasNextLine()){
                String parte = lector.nextLine();
                data.append(parte);
            }
            return data.toString();
        }
        catch (FileNotFoundException e){
            return "1";
        }

    }


    public int escribir(String ruta, String data){
        String info =leer(ruta);
        try{
            FileWriter escritor = new FileWriter(ruta);
            escritor.write(info+data);
            escritor.close();
            return 0;
        }
        catch (IOException e){
            return 1;
        }
    }
    
}