
package monlau.dao;

import monlau.model.Producto;


public interface ProductoDAO {
   public void insert(Producto producto); 
   public void update(Producto producto); 
   public void delete (Integer id); 
   public Producto read(Integer id); 
}
