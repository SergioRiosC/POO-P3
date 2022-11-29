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
            return "-1";
        }

    }


    public int escribir(String ruta, String data){
        try{
            FileWriter escritor = new FileWriter(ruta);
            escritor.write(data);
            escritor.close();
            return 0;
        }
        catch (IOException e){
            return 1;
        }
    }
    public int crear_carpeta(String ruta){
        File archivo = new File(ruta);

        if(archivo.mkdir()){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    public boolean guardarUsuario(Usuario usr){
        
        FileOutputStream fichero = null;
        try {
            fichero = new FileOutputStream("archivos/" + usr.getUsername() + "/estadisticas.txt",false);
            ObjectOutputStream tuberia = new ObjectOutputStream(fichero);
            tuberia.writeObject(usr);
            
            System.out.println(usr.getPassword());
            
            escribir("archivos/" + usr.getUsername() + "/password.txt", usr.getPassword());
            crear_carpeta("archivos/" + usr.getUsername() + "/guerreros");
            
            
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return false;
    }

    public boolean guardarGuerraro(String usr, String nombre, Guerrero guerrero){

        FileOutputStream fichero = null;
        try {
            fichero = new FileOutputStream("archivos/" + usr + "/guerreros/" + nombre + ".txt",false);
            ObjectOutputStream tuberia = new ObjectOutputStream(fichero);
            tuberia.writeObject(guerrero);
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return false;
    }

    public Guerrero buscarGuerrero(String username, String guerrero){
        Guerrero g=null;
        FileInputStream ficheroEntrada=null;

        try{

            ficheroEntrada= new FileInputStream("archivos/" + username + "/guerreros/" + guerrero);
            ObjectInputStream tuberiaEntrada = new ObjectInputStream(ficheroEntrada);
            g=(Guerrero)tuberiaEntrada.readObject();

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return g;
    }
    
    public Usuario buscarusuario(String username){
        Usuario u=null;
        FileInputStream ficheroEntrada=null;
        
        try{
            
            ficheroEntrada= new FileInputStream("archivos/" + username + "/estadisticas.txt");
            
            ObjectInputStream tuberiaEntrada = new ObjectInputStream(ficheroEntrada);
            u=(Usuario)tuberiaEntrada.readObject();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return u;
    }

    public String[] archivosEnDirectorio(String directorio){
        File carpeta = new File(directorio);
        String[] archivos = carpeta.list();
        return archivos;
    }
    
    
    
}