package github.yeori.dtomimic.rule;

public interface IPropertyRule {

	boolean isMatched(String propertyName, int level);
}
