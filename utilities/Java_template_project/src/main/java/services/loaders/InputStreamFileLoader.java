package services.loaders;

import java.io.InputStream;
import services.strategies.load.Loadable;

public abstract class InputStreamFileLoader<T extends Loadable<T>> extends AbstractLoader<T> {

	protected InputStream inputStream;

}
