package opennlp;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.io.File;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import org.junit.Test;

public class OpenNLPTest {
	
	private static final String MODELS = "models";

	@Test
	public void Sentence_Detector_API() throws Exception {
		//Arrange
		File modelFile = new File(MODELS, "en-sent.bin");
		SentenceModel model = new SentenceModel(modelFile);
		SentenceDetector detector = new SentenceDetectorME(model);
		
		//Act
		String[] sentences = detector.sentDetect("First sentence. Second sentence.");
	
		//Assert
		assertThat(sentences.length, is(2));
	}
	
	@Test
	public void Tokenizer_API () throws Exception {
		//Arrange
		File modelFile = new File(MODELS, "en-token.bin");
		TokenizerModel model = new TokenizerModel(modelFile);
		Tokenizer tokenizer = new TokenizerME(model);
		
		//Act
		String[] tokens = tokenizer.tokenize("These are five tokens.");
		
		//Assert
		assertThat(tokens.length, is(5));
	}
	
	@Test
	public void Name_Finder_API () throws Exception {
		fail();
	}
	
	@Test
	public void Document_Categorizer_API () throws Exception {
		fail();
	}
	
	@Test
	public void Part_of_Speech_Tagger_API () throws Exception {
		fail();
	}
	
	@Test
	public void Chunker_API () throws Exception {
		fail();
	}
	
	@Test
	public void Parser_API () throws Exception {
		fail();
	}
	
	@Test
	public void Coreference_Resolution_API () throws Exception {
		fail();
	}
	
}
