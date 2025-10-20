package services.strategies.builder;

@FunctionalInterface
public interface Prompt<T>  {
	public T prompt();
}
