package services.loaders;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import services.strategies.load.Load;
import services.strategies.load.LoadHandler;

public abstract class AbstractLoader<T> {

	public final void load(String filePath, Load<T> loaderFunction, LoadHandler<T> handlerFunction, Collection<T> targetCollection) throws FileNotFoundException,IOException {
		open(filePath);
		read(loaderFunction, handlerFunction, targetCollection);
		close();
	}
	
	public  abstract void open(String filePath) throws FileNotFoundException, IOException;
	public abstract  void read(Load<T> loaderFunction, LoadHandler<T> handlerFunction, Collection<T> targetCollection) throws IOException;
	public abstract void close() throws IOException;

}
