
package monlau;


import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOimpl;
import monlau.model.Producto;

public class ProductoManager {

   
    public static void main(String[] args) {
      ProductoDAO producto =new ProductoDAOimpl();
        
        //agregar nuevo producto
       producto.insert(new Producto(300,"Pollo",10.0));
       
        producto.delete(200);
        //obtener el producto con el ID = 100
        Producto p = producto.read(300);
        //System.out.println(p.toString());
    }
    
}
