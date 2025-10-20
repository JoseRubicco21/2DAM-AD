package services.strategies.load;

import java.util.Collection;

@FunctionalInterface
public interface LoadHandler<T>  {
	public void handle(T targetObject, Collection<T> targetCollection);
}
