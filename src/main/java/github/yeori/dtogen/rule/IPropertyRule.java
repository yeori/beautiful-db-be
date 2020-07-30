package github.yeori.dtogen.rule;

public interface IPropertyRule {

	boolean isMatched(String propertyName, int level);
}
