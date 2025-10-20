package services.strategies.load;

public interface Loadable<T> {
	public T loadFromTextFile(Object dataSource);
	public T loadFromByteFile(Object dataSource);
	public T loadFromSerializedFile(Object dataSource);
	public T loadFromDatabase(Object dataSource);
}
