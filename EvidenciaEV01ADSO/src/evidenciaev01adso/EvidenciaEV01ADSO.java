/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package evidenciaev01adso;

/**
 *
 * @author B$Z
 */
import java.sql.*;

public class EvidenciaEV01ADSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        String usuario="root";
        String password="";
        String url="jdbc:mysql://localhost:3306/pcinnovation";
        Connection conexion;
        Statement statement;
        ResultSet rs;
        
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(EvidenciaEV01ADSO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        try {
            conexion=DriverManager.getConnection(url,usuario,password);
            statement=conexion.createStatement();
            rs=statement.executeQuery("SELECT * FROM PRODUCTOS");
            rs.next();
            do{
                System.out.println(rs.getInt("id_producto")+":"+ rs.getString("nombre"));          
            }while (rs.next());
            
            
            //Aqui va el INSERT
            
           statement.executeUpdate("INSERT INTO PRODUCTOS (nombre, descripcion, precio, stock, id_categoria, marca, imagen_url) " +
    "VALUES ('Mouse Logitech G203', 'Mouse gamer RGB con sensor óptico', 120000, 20, 3, 'Logitech', 'imagenes/g203.jpg')");
           rs=statement.executeQuery("SELECT * FROM PRODUCTOS");
           rs.next();
           do{
           System.out.println(rs.getInt("id_producto") + ":" + rs.getString("nombre"));
          }while (rs.next());
          
           
           //UPDATE
           
           statement.executeUpdate("UPDATE productos SET id_producto='Disco Solido ADATA 1TB' WEHERE nombre=1");        
           rs=statement.executeQuery("SELECT * FROM PRODUCTOS");
           rs.next();
           do{
           System.out.println(rs.getInt("id_producto") + ":" + rs.getString("nombre"));
          }while (rs.next());
          
           
           // DELETE 
           
           
             statement.executeUpdate("DELETE from productos WHERE id=2");        
           rs=statement.executeQuery("SELECT * FROM PRODUCTOS");
           rs.next();
           do{
           System.out.println(rs.getInt("id_producto") + ":" + rs.getString("nombre"));
          }while (rs.next());
          
           
           
          } catch (SQLException ex) {
            System.getLogger(EvidenciaEV01ADSO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
                
    }
    
    
}
