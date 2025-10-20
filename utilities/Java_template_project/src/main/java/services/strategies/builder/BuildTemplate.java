package services.strategies.builder;

public class BuildTemplate {
	public static <T> T build(Prompt<T> promptFunction, Validator<T> validatorFunction) throws Exception {
		boolean state = false;
		T value;
		do {
			value = promptFunction.prompt();			
			state  = validatorFunction.validate(value);
		} while (!state);
		return value;
	}
}
