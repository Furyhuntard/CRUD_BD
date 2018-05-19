import java.sql.*;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
public class ModeloDB extends ModeloAbs
{
	Scanner teclado = new Scanner(System.in);
	final static String username = "root";
	final static String password = "namek3000";
	final static String url = "jdbc:mysql://localhost/Productosdb?serverTimezone=GMT";
	static Connection conexion = null;
	static PreparedStatement stmt = null;
    public ModeloDB()
    {
    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //Cargo driver
    		conexion = DriverManager.getConnection(url, username, password); //Establezco conexión
    		System.out.println("Conexcion establecida");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    // INSERT
    public boolean insertarProducto ( Producto p){
    	String consulta = "INSERT INTO PRODUCTOS" + " VALUES" + " (?,?,?,?,?)";
    	try
    	{
    		stmt = conexion.prepareStatement(consulta);
    		stmt.setInt(1,p.getCodigo());
            stmt.setString(2,p.getNombre());
            stmt.setInt(3,p.getStock());
            stmt.setInt(4,p.getStock_min());
            stmt.setFloat(5,p.getPrecio());
            stmt.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    return false;    
    }
    
    
    // DELETE
    boolean borrarProducto ( int codigo ){
    	String consulta = "DELETE FROM PRODUCTOS WHERE CODIGO = " +codigo;
    	try
    	{
    		stmt = conexion.prepareStatement(consulta);
            stmt.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    return false;    
    }
    
    // SELECT
    public Producto buscarProducto ( int codigo){
    	String consulta = "SELECT * FROM PRODUCTOS WHERE CODIGO = " +codigo;
    	Producto p = new Producto();
    	try
    	{
    		stmt = conexion.prepareStatement(consulta);
    		ResultSet rset = stmt.executeQuery();
    		while (rset.next())
    		{
        		p.setCodigo(rset.getInt(1));
        		p.setNombre(rset.getString(2));
        		p.setStock(rset.getInt(3));
        		p.setStock_min(rset.getInt(4));
        		p.setPrecio(rset.getFloat(5));
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    return p;    
    }
    
    
    //SELECT
    void listarProductos (){
    	String consulta = "SELECT * FROM PRODUCTOS";
    	try
    	{
    		stmt = conexion.prepareStatement(consulta);
            stmt.executeQuery();
            ResultSet rset = stmt.executeQuery();
            while(rset.next())
            {
            	System.out.print(rset.getInt(1)+ " ");
            	System.out.print(rset.getString(2)+ " ");
            	System.out.print(rset.getInt(3)+ " ");
            	System.out.print(rset.getInt(4)+ " ");
            	System.out.print(rset.getFloat(5));
            	System.out.println("");
            }
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        
    }
    
    void listarPocoStock() {
    	
    }
    
    //UPDATE
    boolean modificarProducto (Producto nuevo){  
    return false;    
    }
    
    // Devuelvo un Iterador de una ArrayList con los resultados
    // copiados de Rset al ArrayList
     Iterator <Producto> getIterator(){
     ArrayList <Producto> lista = new ArrayList<Producto>();
       // Relleno el array list con los resultados de al consulta
       
       return lista.iterator();
     }
}