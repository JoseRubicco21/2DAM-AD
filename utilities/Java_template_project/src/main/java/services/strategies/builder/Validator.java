package services.strategies.builder;

@FunctionalInterface	
public interface Validator<T> {
	public boolean validate(T value) throws Exception;
}
