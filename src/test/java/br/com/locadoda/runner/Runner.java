package br.com.locadoda.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",
							features="src/test/resources/features/"
									+ "ProjetoLocadora.feature",
							glue = "br.com.locadora.steps",
//							tags = "@esse",//executa apenas os cenarios com essa tags 
							monochrome=true,
							snippets = SnippetType.CAMELCASE,
							dryRun = false,
							strict = false
							)
public class Runner {

}
