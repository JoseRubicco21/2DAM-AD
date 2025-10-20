package services.strategies.load;

@FunctionalInterface
public interface Load<T> {
	public <D> T load(D dataSource);
}
