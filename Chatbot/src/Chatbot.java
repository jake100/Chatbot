import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class Chatbot {
	private static String name, botName = "chatbot";
	private static int age = 0;
    private static final String[] GREETINGS = {"Hi", "Hello", "Hey", "Hi there", "Greetings"};
    private static final String[] FAREWELLS = {"Goodbye", "See you later", "Bye", "Farewell"};
    private static final String[] RESPONSES = {
        "How can I help you today?",
        "What can I do for you?",
        "What's up?",
        "How's your day going?",
        "What's on your mind?"
    };
    private static String[] jokes = {
    		"Why did the scarecrow win an award? Because he was outstanding in his field!",
    		  "Why do seagulls fly over the sea? Because if they flew over the bay, they'd be bagels!",
    		  "Why did the tomato turn red? Because it saw the salad dressing!",
    		  "Why do cows wear bells? Because their horns don't work!",
    		  "Why did the cookie go to the doctor? Because it was feeling crumbly!",
    		  "Why did the man put his money in the freezer? He wanted cold hard cash!",
    		  "Why don't scientists trust atoms? Because they make up everything!",
    		  "Why did the bicycle fall over? Because it was two-tired!",
    		  "Why was the computer cold? Because it left its Windows open!",
    		  "Why did the frog call his insurance company? He had a jump in his car!",
    		  "Why did the math book look so mad? Because it had too many problems!",
    		  "Why did the cheese stand alone? Because it was a-mazing!",
    		  "Why did the man put his money in the blender? He wanted to make liquid assets!",
    		  "Why don't oysters share their pearls? Because they're shellfish!",
    		  "Why did the scarecrow win an award? Because he was outstanding in his field!",
    		  "Why did the chicken cross the playground? To get to the other slide!",
    		  "Why did the dog chase its tail? Because it was after its own booty!",
    		  "Why did the cookie go to the hospital? Because it felt crumbly!",
    		  "Why did the hipster burn his tongue? He drank his coffee before it was cool!",
    		  "Why did the man put his money in the blender? He wanted to make liquid assets!"
    	  };
    private static String[] trivia = {
    		"The Great Wall of China is the longest wall in the world, stretching over 13,000 miles.",
    		  "The shortest war in recorded history lasted only 38 minutes, between Britain and Zanzibar on 27 August 1896.",
    		  "The Mona Lisa is a half-length portrait painting by the Italian artist Leonardo da Vinci, widely considered the finest example of Renaissance portraiture.",
    		  "The first recorded game of basketball was played in December 1891 in Springfield, Massachusetts.",
    		  "The Amazon rainforest produces more than 20% of the world's oxygen supply.",
    		  "In 1665, the Great Plague of London claimed 100,000 lives.",
    		  "The planet Venus rotates in the opposite direction of most other planets.",
    		  "The first recorded use of a bicycle was in 1817 by German baron Karl von Drais.",
    		  "The Declaration of Independence was signed on July 4, 1776.",
    		  "The total distance traveled by a creased ping pong ball is approximately 186 miles.",
    		  "The highest mountain in the solar system is Olympus Mons on Mars, which is three times taller than Mount Everest.",
    		  "The first human-made object to enter space was the Soviet Union's Sputnik 1 satellite in 1957.",
    		  "In 1997, scientists discovered that the universe is expanding at an accelerating rate.",
    		  "The world's largest ocean is the Pacific Ocean, covering an area of 63,800,000 square miles.",
    		  "The smallest country in the world by land area is the Vatican City, measuring just 0.17 square miles.",
    		  "The first successful powered flight took place on December 17, 1903, by the Wright brothers in Kitty Hawk, North Carolina.",
    		  "The fastest land animal is the cheetah, capable of reaching speeds of up to 60 miles per hour.",
    		  "The coldest place on Earth is the East Antarctic Plateau, where temperatures can reach as low as minus 128.6 degrees Fahrenheit.",
    		  "The oldest university in the world is the University of Bologna, founded in 1088 in Italy.",
    		  "The largest living organism in the world is a fungus called Armillaria ostoyae, which covers over 2,200 acres in Oregon."
    	  };
    private static String[] programmingLanguages = {
    		"Java",
    		"C",
    		"C++",
    		"Python",
    		"JavaScript",
    		"Ruby",
    		"C#",
    		"PHP",
    		"Swift",
    		"Go",
    		"Kotlin",
    		"TypeScript",
    		"Objective-C",
    		"R",
    		"Scala",
    		"Rust",
    		"Dart",
    		"Elixir",
    		"Lua"
    	  };
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String response;
    	System.out.print("What is your name? ");
        name = scanner.nextLine();

        System.out.println("Hi " + name + "! I'm your Chatbot. How can I help you today?");
        while (true) {
            input = scanner.nextLine();
            response = getResponse(input);
            System.out.println(response);
            if (isFarewell(input)) {
                break;            }
        }
        scanner.close();
    }

    private static String getResponse(String input) {
        input = input.toLowerCase();
        if(!massReact(input).equals("null"))
        {
        	return massReact(input);
        }
        if(!regexQuestion(input).equals("null"))
        {
        	return regexQuestion(input);
        }
        if(!regexCommand(input).equals("null"))
        {
        	return regexCommand(input);
        }
        if(!regexSimpleCommand(input).equals("null"))
        {
        	return regexSimpleCommand(input);
        }
        for (String greeting : GREETINGS) {
            if (input.startsWith(greeting.toLowerCase())) {
                return RESPONSES[(int)(Math.random() * RESPONSES.length)];
            }
        }
        for (String farewell : FAREWELLS) {
            if (input.equals(farewell.toLowerCase())) {
                return "Goodbye! Have a great day! " + name;
            }
        }
        if(!nlpReact(input).equals("null"))
        {
        	return nlpReact(input);
        }
        return "I'm sorry, I don't understand what you're saying. Can you rephrase?";
    }

    private static boolean isFarewell(String input) {
        input = input.toLowerCase();
        for (String farewell : FAREWELLS) {
            if (input.equals(farewell.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    private static String regexCommand(String input)
    {
    	Pattern setName = Pattern.compile("set name (to )?(\\w{1,18})");
    	Pattern setAge = Pattern.compile("set age (to )?(\\d{1,3})");
    	
    	Matcher nameMatcher = setName.matcher(input);
    	Matcher ageMatcher = setAge.matcher(input);
    	
        if (nameMatcher.find()) {
        	botName = nameMatcher.group(2);
            return "Name changed to " + nameMatcher.group(2);
        }
        if (ageMatcher.find()) {
        	age = Integer.parseInt(ageMatcher.group(2));
            return "Age changed to " + ageMatcher.group(2);
        }
        return "null";
    }
    private static String regexSimpleCommand(String input)
    {

    	Pattern tellJoke = Pattern.compile("tell( me)? a joke\\.{0,1}");
    	Pattern tellTrivia = Pattern.compile("tell( me)? a fact\\.{0,1}");
    	Pattern tellProgrammingLanguage = Pattern.compile("give me a programming language\\.{0,1}");
    	
    	Matcher jokeMatcher = tellJoke.matcher(input);
    	Matcher triviaMatcher = tellTrivia.matcher(input);
    	Matcher programmingLanguageMatcher = tellProgrammingLanguage.matcher(input);
    	
        if (jokeMatcher.find()) {
        	Random random = new Random();
            int jokeIndex = random.nextInt(jokes.length);
            return jokes[jokeIndex];
        }
        if (triviaMatcher.find()) {
        	Random random = new Random();
            int triviaIndex = random.nextInt(trivia.length);
            return trivia[triviaIndex];
        }
        if (programmingLanguageMatcher.find()) {
        	Random random = new Random();
            int langIndex = random.nextInt(programmingLanguages.length);
            return programmingLanguages[langIndex];
        }
        return "null";
    }
    private static String regexQuestion(String input)
    {
        Pattern whatTime = Pattern.compile("what time is it\\?{0,1}");
        Pattern whatDate = Pattern.compile("what date is it\\?{0,1}");
        Pattern whatDay = Pattern.compile("what day is it\\?{0,1}");
        Pattern howOld = Pattern.compile("how old are you\\?{0,1}");
        Pattern whatName = Pattern.compile("what is your name\\?{0,1}");
        
        // Use matcher to match user input with regex patterns
        Matcher timeMatcher = whatTime.matcher(input);
        Matcher dateMatcher = whatDate.matcher(input);
        Matcher dayMatcher = whatDay.matcher(input);
        Matcher oldMatcher = howOld.matcher(input);
        Matcher nameMatcher = whatName.matcher(input);
        
        // Check if user input matches any of the regex patterns and respond accordingly
        if (timeMatcher.find()) {
        	Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            return "Current time is: " + dateFormat.format(date);
        } else if (dateMatcher.find()) {
        	Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return "Today's date is: " + dateFormat.format(date);
        } else if (dayMatcher.find()) {
            Date date = new Date();
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            String dayOfWeek = dayFormat.format(date);
            return "Today is " + dayOfWeek;
        } else if (oldMatcher.find()) {
          return age + "";
        } else if (nameMatcher.find()) {
          return botName;
        } 
        return "null";	  
    }
    private static String nlpReact(String input)
    {
    	Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        
        Annotation annotation = pipeline.process(input);
        int mainSentiment = 0;
        
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
          Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
          int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
          String partText = sentence.toString();
          if (partText.length() > mainSentiment) {
            mainSentiment = sentiment;
          }
        }
        
        switch (mainSentiment) {
        case 0:
          return "null";
        case 1:
          return "Are you angry?";

        case 2:
          return "Are you sad?";
        case 3:
          return "That's good to hear!";
        case 4:
          return "Awesome!";
        default:
          return "null";
      }
    }
    public static boolean isInArray(String input, String[] array) {
        for (String item : array) {
            if (input.equals(item)) {
                return true;
            }
        }
        return false;
    }
    private static String massReact(String input)
    {
    	if(isInArray(input, StringArrays.basic))
    	{
    		return "That's childish.";
    	}
    	if(isInArray(input, StringArrays.requests) || isInArray(input, StringArrays.search))
    	{
    		return "Google it.";
    	}
    	if(isInArray(input, StringArrays.programmingTerms))
    	{
    		return "That's technical...";
    	}
    	
    	return "null";
    }
}
