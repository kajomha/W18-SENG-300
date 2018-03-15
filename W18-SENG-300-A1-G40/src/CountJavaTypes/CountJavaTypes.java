package CountJavaTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import javax.swing.JFileChooser;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;

public class CountJavaTypes {
	
	 public static void main(String[] args) throws FileNotFoundException{
		 
		 // Store command line arguments
		 String pathname = args[0];
		 String type = args[1];
		
		 //Print confirmations of recieved input
		 System.out.println("You have selected the following directory:\n\t" + pathname + "\n");
		 System.out.println("You have chosen to check the java type: " + type + "\n\n");
		 
		 //Open the directory to read files from
		 File directory = new File(pathname);
		 //Lists all files from directory
		 File[] list = directory.listFiles();
		 
		 //Boolean to check if directory has a .java file
		 boolean no_file = true;
		 //counters
		 int declarations = 0;
		 int references = 0;
		 
		 //Loop to iterate though files in the directory
		 for (int i = 0; i<list.length; i++) {
			 if (list[i].isFile()) {
				 
				 //Look for files with .java extension
				 int temp = list[i].getName().lastIndexOf('.');
				 String filetype = list[i].getName().substring(temp+1);
				 
				 if (filetype.contentEquals("java")) {
					 //Set boolean to show .java file was found
					 no_file = false;
					 
					 //Use scanner to read code from file. Stores whole doc in a string
					 String code = new Scanner(list[i]).useDelimiter("\\A").next();

					 //Parse method is called to invoke the AST parser(see below)
					 //Method returns a list of its containing the counts
					 int[] counters = parse(code,type);
					 //Counters are updated
					 declarations = declarations + counters[0];
					 references = references + counters[1];
				 }
			 }
		 }
		 //Print out results
		 if (no_file == true) {
			 System.out.println("This directory does not contian any files with the exptension: '.java'");
		 }
		 
		 else {
			 System.out.println("\n" + type + ". Declarations found: " + declarations + "; references found: " + references);
		 }
	  }  
	 
	 //Parse method
	 public static int[] parse(String str, String type) {
		 
		 //Create AST Parser
		    
			ASTParser parser = ASTParser.newParser(AST.JLS9);
			parser.setSource(str.toCharArray());
			
			Map<String, String> options = JavaCore.getOptions();
			JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
			parser.setCompilerOptions(options);
			
			parser.setBindingsRecovery(true);
			parser.setResolveBindings(true);
			parser.setKind(ASTParser.K_COMPILATION_UNIT);		
			
			//Initialize counters to 0
			int[] counters = {0,0};
			
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	 
			//Visit string
			cu.accept(new ASTVisitor() {
					 
				//Visits Class and Interface declaration, i.e. the assign1 class.
				public boolean visit(TypeDeclaration node) {
					
					//Updates declaration counter if type given is class name
					if (node.getName().getFullyQualifiedName().equals(type)) {
						counters[0] = counters[0] + 1;
					}
					
					return true;				

				}			
				
				//Visits annotations
				public boolean visit(AnnotationTypeDeclaration node) {
					
					//Updates declaration counter if type given is annotation name
					if (node.getName().getFullyQualifiedName().equals(type)) {
						counters[0] = counters[0] + 1;
					}	
					
					return false;				
				}
				
				//Visits Enumerations
				public boolean visit(EnumDeclaration node) {
					//Updates declaration counter if type given is enum name
					if (node.getName().getFullyQualifiedName().equals(type)) {
						counters[0] = counters[0] + 1;
					}
					
					return false;				
				}
				
				//Used to count references after getting variable names
				Set<String> names = new HashSet<String>();
				
				//Looks like we're not supposed to count primitive + String var declarations as type declarations
				//The following arary turned list is used to test if give type is one of them
				String[] primitive = new String[] {"int", "char", "short", "double", "boolean", "byte", "float", "long", "String" };
				List<String> list = Arrays.asList(primitive);
					
				
				//Count varriable declarations and get names
				public boolean visit(VariableDeclarationFragment node) {
					
					//Get parent node of VariableDeclarationFragment to get the data type
					String field = node.getParent().toString();
					
					//The following is a get around since I havent been able to resolve bindings
					//It simply rediects an input of java.lang.String to become String
					String type2;
					
					if (type.equals("java.lang.String")) {
						type2 = "String";
					}else {
						type2 = type;
					}
					
					//Checks to see of gotten parent string, which houses the entire variable declaration,
					//starts with type2 + [] to get the type of the declared variable
					// the + " " is used so that an input of "int" does not trigger "int[]"
					//This is a work around as well
					if (field.startsWith(type2 + " ")) {
						
						//varname is added to hash in order to count references later
						SimpleName name = node.getName();
						this.names.add(name.getIdentifier());
						
						//This will make sure that if input is primitive or String, declarations count is not incremented
						//and instead, reference count is incremented
						if (list.contains(type2) == false) {
							counters[0] = counters[0] + 1;
						}else {
							counters[1] = counters[1] + 1;
						}
					}
					
					return false;				
				}	
	 
				//Count references for all vars for given data type
				public boolean visit(SimpleName node) {
					//Look for references to the appropriate variable name
					if (this.names.contains(node.getIdentifier())) {
						counters[1] = counters[1] + 1;
						
					}
					
					return true;
				}					
			});
			//Return updated counters
			return counters;
		}
}