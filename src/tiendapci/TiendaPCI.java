/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiendapci;

/**
 *
 * @author B$Z
 */
import java.sql.*;
public class TiendaPCI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {   
            
            String usuario="root";
            String password="";        
            String url="jdbc:mysql://localhost:3306/PCInnovation";
            Connection conexion;
            Statement statement;
            ResultSet rs;
            
    
       
        try {
            // TODO code application logic here
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(TiendaPCI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        try {
            conexion=DriverManager.getConnection(url,usuario,password);
            statement=conexion.createStatement();
            rs=statement.executeQuery("SELECT * FROM usuarios");
            rs.next ();
            do{
                System.out.println(rs.getInt("id_cliente")+":"+ rs.getNString("correo"));
            }while (rs.next());
             
            //INSERT
            statement.executeUpdate("INSERT INTO USUARIOS VALUES (6,'ingrid177@hotmail.com','152424')");
            System.out.println("");
            rs=statement.executeQuery("SELECT * FROM usuarios");
            rs.next ();
            do{
                System.out.println(rs.getInt("id_cliente")+":"+ rs.getNString("correo"));
            }while (rs.next());
                   
            //UPDATE

            statement.executeUpdate("UPDATE usuarios SET correo='daniela75@gmail.com' WHERE id_cliente=3");
            System.out.println("");
            rs=statement.executeQuery("SELECT * FROM usuarios");
            rs.next ();
            do{
                System.out.println(rs.getInt("id_cliente")+":"+ rs.getNString("correo"));
            }while (rs.next());            
            
            //DELETE
            
            statement.executeUpdate("DELETE from usuarios WHERE id_cliente=5");
            System.out.println("");
            rs=statement.executeQuery("SELECT * FROM usuarios");
            rs.next ();
            do{
                System.out.println(rs.getInt("id_cliente")+":"+ rs.getNString("correo"));
            }while (rs.next());
            
            
        } catch (SQLException ex) {
            System.getLogger(TiendaPCI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
