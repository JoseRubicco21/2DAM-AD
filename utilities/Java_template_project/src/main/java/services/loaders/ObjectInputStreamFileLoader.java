package services.loaders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;

import services.strategies.load.Load;
import services.strategies.load.LoadHandler;
import services.strategies.load.Loadable;

public class ObjectInputStreamFileLoader<T extends Loadable<T>> extends InputStreamFileLoader<T> {

		private ObjectInputStream dataStream;
	@Override
	public void open(String filePath) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		this.inputStream = new FileInputStream(filePath);
		this.dataStream = new ObjectInputStream(this.inputStream);
	}

	@Override
	public void read(Load<T> loaderFunction, LoadHandler<T> handlerFunction, Collection<T> targetCollection)
			throws IOException {
		handlerFunction.handle(loaderFunction.load(dataStream), targetCollection);
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if(this.inputStream != null) {
			this.inputStream.close();
		}
		
		if(this.dataStream !=  null) {
			this.dataStream.close();
		}
	}

}
