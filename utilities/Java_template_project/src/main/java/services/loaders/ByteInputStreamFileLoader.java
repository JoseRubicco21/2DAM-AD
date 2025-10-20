package services.loaders;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;

import services.strategies.load.Load;
import services.strategies.load.LoadHandler;
import services.strategies.load.Loadable;

public class ByteInputStreamFileLoader<T extends Loadable<T>> extends InputStreamFileLoader<T> {

	private DataInputStream dataStream;
	@Override
	public void open(String filePath) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		this.inputStream = new FileInputStream(filePath);
		this.dataStream = new DataInputStream(inputStream);
	}

	@Override
	public void read(Load<T> loaderFunction, LoadHandler<T> handlerFunction, Collection<T> targetCollection) throws IOException {
		while(loaderFunction.load(dataStream) != null) {
			handlerFunction.handle(loaderFunction.load(dataStream), targetCollection);
			}
		
	}

	@Override
	public void close() throws IOException {
		if(this.inputStream != null) {
			this.inputStream.close();
		}
		if(this.dataStream != null) {
			this.dataStream.close();
		}
	}

}
