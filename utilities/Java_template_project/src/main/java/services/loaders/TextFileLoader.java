package services.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import services.strategies.load.Load;
import services.strategies.load.LoadHandler;
import services.strategies.load.Loadable;

public class TextFileLoader<T extends Loadable<T>> extends AbstractLoader<T> {

	private BufferedReader bufferedReader;
	
	@Override
	public void open(String filePath) throws FileNotFoundException {
				this.bufferedReader = new BufferedReader(new FileReader(filePath));
	}

	@Override
	public void read(Load<T> loaderFunction, LoadHandler<T> handlerFunction, Collection<T> targetCollection) throws IOException{
		String line;
			while((line = this.bufferedReader.readLine()) != null) {
				//function that calls: LoadBasedOnLine,
				
				handlerFunction.handle(loaderFunction.load(line), targetCollection);
		}

	}
	@Override
	public void close() throws IOException {
			this.bufferedReader.close();
		}

}
