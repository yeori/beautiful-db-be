package github.yeori.dtomimic.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import github.yeori.dtomimic.DtoMimicException;

public class Rules {

	private static final IPropertyRule EMPTY_RULE = (propName, level) -> false;

	public static IPropertyRule parseRules(String ... ruleSpecs) {
		List<IPropertyRule> rules = new ArrayList<>();
		for (String spec : ruleSpecs) {
			rules.add(new ExactMatchingRule(spec.trim()));
		}
		return new CompositeRule(rules);
	}

	private static String[] delim(String spec) {
		String [] tokens = spec.split("\\.");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim();
			if(tokens[i].length() == 0) {
				throw new DtoMimicException("empty property found : [%s]", spec);
			}
		}
		return tokens;
	}

	public static IPropertyRule emptyRule() {
		return EMPTY_RULE;
	}
}
