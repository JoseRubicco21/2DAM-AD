package controllers.db;

import java.sql.SQLException;
import javax.sql.RowSet;

public interface CRUD<T> {
	// Exceptions of Crud operations are handled on the master driver composite.
	
	public void create(RowSet rs, T register) throws SQLException; 
	
	// For models that do not have relationship coming into them
	public T read(RowSet rs) throws SQLException ; 
	public void update(RowSet rs, T register) throws SQLException; 
	public void delete(RowSet rs) throws SQLException;
	
	// For models that do have relationships coming into them. 
	// When an object is composed of others objects and thus needs to delegate such operation to corresponding driver / controller
	// IMPORTANT: These shouldn't be implemented every time. These for those special cases where
	// one wants cascading deletion cascading update and the database does not support it.
	// Please be careful implementing these and in the case of doing make sure you're compliant
	// with the data integrity on the database. We don't want orphans or miss-reference.
	public T read(RowSet rs, JBDCTemplateController ... drivers) throws SQLException; 
	public void update(RowSet rs, T register, JBDCTemplateController ... drivers) throws SQLException;
	public void delete(RowSet rs, JBDCTemplateController ... drivers);
	
	public void create(); 
	public void read();
	public void update();
	public void delete();
}
