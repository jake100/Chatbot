import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;


public class NLPTest {
	public static void main(String[] args)
	{
		System.out.println(nlpSpelling("fuck it all"));
	}
	private static String nlpSpelling(String input)
    {
		StanfordCoreNLP pipeline;
		Annotation document = new Annotation(input);
	    pipeline.annotate(document);
	    
	    // Get the list of sentences
	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	    
	    // Iterate over each sentence
	    for (CoreMap sentence : sentences) {
	      // Get the list of tokens in the sentence
	      List<CoreLabel> tokens = sentence.get(TokensAnnotation.class);
	      
	      // Iterate over each token
	      for (CoreLabel token : tokens) {
	        // Check if the token is a word
	        if (!token.word().matches("[A-Za-z]+")) {
	          continue;
	        }
	        
	        // Check if the word is spelled correctly
	        if (!token.get(SpellCheckAnnotation.class).isCorrect()) {
	          System.out.println("Incorrect word: " + token.word());
	        }
	      }
    }
}
