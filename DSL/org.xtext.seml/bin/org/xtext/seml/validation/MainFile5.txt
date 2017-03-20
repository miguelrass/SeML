package org.xtext.seml.validation;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.mindswap.pellet.utils.progress.ConsoleProgressMonitor;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.ReasonerProgressMonitor;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.semanticweb.owlapi.search.Searcher;
import org.semanticweb.owlapi.model.parameters.Imports;

import com.clarkparsia.pellet.owlapi.PelletReasoner;
import com.clarkparsia.pellet.owlapi.PelletReasonerFactory;

import riotcmd.perftokens;





/*import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;*/

public class MainFile5{

	public static void main(String[] args) {
			try {
				
				//======================================================================================================================= LOAD ONTOLOGIA
				//String path = "C:\\Users\\Miguel\\Desktop\\Ontologia\\Thesis\\owl files\\MetaModelTest1.owl";
				String path = "C:\\Users\\Miguel\\Desktop\\Ontologia\\Thesis\\owl files\\UpperOnt.owl";
				//String path = "C:\\Users\\Miguel\\Desktop\\Ontologia\\prototipo synth\\synth.owl";
				//String path2 = "C:\\Users\\Miguel\\Desktop\\Ontologia\\prototipo synth\\main.owl";
				//String ToppingsClassIRI = "http://www.semanticweb.org/miguel/ontologies/2016/10/untitled-ontology-34#Process";
				

				System.out.println("Trying to load: " + path);
				
				OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
				File ontfile = new File(path);
				//File ontbase = new File(path2);
				//manager.loadOntologyFromOntologyDocument(ontbase);
			    OWLOntology ontology =  manager.loadOntologyFromOntologyDocument(ontfile);
			    System.out.println("Ontology Format:" + manager.getOntologyFormat(ontology));
			    
			    //======================================================================================================================= BASICO: CLASSES + INDIVIDUOS
			    System.out.println("\nPrinting your classes:");
			    for (OWLClass cls : ontology.getClassesInSignature()) { //@deprecated, devia usar os novos métodos de stream
			         System.out.println(cls + "; ");
			         printClassAnnotations(cls, ontology);
			    }
			    
			    System.out.println("\nPrinting your individuals:");
			    for (OWLNamedIndividual cls : ontology.getIndividualsInSignature()) { //@deprecated, devia usar os novos métodos de stream
			    	System.out.println(cls + "; ");
			    }
			    
			    //======================================================================================================================= AVANÇADO: AXIOMAS
			    System.out.println("\n There are " + ontology.getAxiomCount() + " axioms in ROOT Ontology");
		        
		        System.out.println("\nIterating axioms of ROOT Ontology:");

		        Set<OWLAxiom> axiomas=ontology.getAxioms();
		        Iterator<OWLAxiom> it_axiomas=axiomas.iterator();
		        
		        int cnt = 0;
		        
		        while(!it_axiomas.hasNext()) { //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% DEACTIVATED
		            OWLAxiom tempAx= it_axiomas.next();

	            	//OWLNamedIndividual ind = tempAx.getIndividualsInSignature().iterator().next();
	            	
		            
		            System.out.print(cnt + " " + tempAx.getAxiomType().getName());
		            if(tempAx.isLogicalAxiom()) System.out.println(" (É um axioma lógico)"); else System.out.println();
	                if(!tempAx.getIndividualsInSignature().isEmpty()) System.out.println("\n" + cnt +   " Individuo: " + tempAx.getIndividualsInSignature());
	                if(!tempAx.getDataPropertiesInSignature().isEmpty()) System.out.println(cnt +   " DataProp:  " + tempAx.getDataPropertiesInSignature());
	                if(!tempAx.getObjectPropertiesInSignature().isEmpty()) System.out.println(cnt + " ObjProp:   " + tempAx.getObjectPropertiesInSignature());
	                if(!tempAx.getAnnotationPropertiesInSignature().isEmpty()) System.out.println(cnt + " Anotacoes: " + tempAx.getAnnotationPropertiesInSignature());
	                if(!tempAx.getClassesInSignature().isEmpty()) System.out.println(cnt + " Classes:   " + tempAx.getClassesInSignature());
	                if(!tempAx.getDatatypesInSignature().isEmpty()) System.out.println(cnt + " Datatypes: " + tempAx.getDatatypesInSignature());
	                if(!tempAx.getSignature().isEmpty()) System.out.println(cnt + " Signature: " + tempAx.getSignature());
	                System.out.println();
	                cnt++;

		        }
			    
		        //======================================================================================================================= STRUCTURAL REASONER
			    OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
			    OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
			    // At creation time, an OWLReasoner will load the axioms in the root ontology imports closure. It will attach itself as a listener to the OWLOntologyManager 
			    // that manages the root ontology. The reasoner will listen to any OWLOntologyChanges and respond appropriately to them before answering any queries. If the 
			    // BufferingMode of the reasoner (the answer to getBufferingMode() is BufferingMode.NON_BUFFERING) the ontology changes are processed by the reasoner immediately
			    // so that any queries asked after the changes are answered with respect to the changed ontologies. If the BufferingMode of the reasoner is BufferingMode.BUFFERING 
			    // then ontology changes are stored in a buffer and are only taken into consideration when the buffer is flushed with the flush() method. When reasoning, axioms in
			    // the root ontology imports closure, minus the axioms returned by the getPendingAxiomAdditions() method, plus the axioms returned by the getPendingAxiomRemovals() 
			    // are taken into consideration. 
			    reasoner.precomputeInferences();
			    
			   
					    
			    System.out.println("\nReasoner: " + reasoner.getReasonerName() + " " + reasoner.getReasonerVersion());
			    
			    System.out.println("\nConsistent: " + reasoner.isConsistent());
			   
		        Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses(); //classes equivalent to owl:Nothing
		        Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom(); //unsatisfiable classes excluding owl:Nothing
		        if (!unsatisfiable.isEmpty()) {
		            System.out.println("The following classes are unsatisfiable: ");
		            for (OWLClass cls : unsatisfiable) {
		                 System.out.println(" " + cls);
		            }
		        } else {
		            System.out.println("There are no unsatisfiable classes");
		        }
		        
		        System.out.println("\nPrinting your individuals:");
		        OWLDataFactory fac2 = manager.getOWLDataFactory();
		        
		        OWLClass taco2 = fac2.getOWLClass(/*IRI.create(ToppingsClassIRI)*/ IRI.create(ontology.getOntologyID().getOntologyIRI().get() + "#Feature"));
		        System.out.println("Anotacoes no FEATURE: " + taco2.getAnnotationPropertiesInSignature());
		        
		        taco2 = fac2.getOWLClass(/*IRI.create(ToppingsClassIRI)*/ IRI.create(ontology.getOntologyID().getOntologyIRI().get() + "#Process"));
			    NodeSet<OWLNamedIndividual> subTaco2 = reasoner.getInstances(taco2, true);
		        Set<OWLNamedIndividual> tacos2 = subTaco2.getFlattened();
		        for (OWLNamedIndividual cls : tacos2) System.out.println(" " + cls);
		        
		        //=========================================================================================================================== Annotations
		 
		        
		        //OWLAnnotationProperty label = fac2.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());
		        for (OWLClass cls : ontology.getClassesInSignature()) {
		            // Get the annotations on the class that use the label property
		            for (OWLOntology o : ontology.getImportsClosure()) { //gets all related ontologies (imports)
		                for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(cls.getIRI())) { //gets annotations from every ontology file
		                    if (annotation.getValue() instanceof OWLLiteral) {
		                    	
		               
		                    	
		                        OWLLiteral val = (OWLLiteral) annotation.getValue();
		                        //if (val.hasLang("pt")) {
		                             System.out.println(cls + " -> Literal: " + val.getLiteral());
		                        //}
		                            System.out.println("AnnotProp: " + annotation.getAnnotationPropertiesInSignature());
		                            System.out.println("DataType:  " + annotation.getDatatypesInSignature());
				                    System.out.println("Signature: " + annotation.getSignature());
				                   System.out.println();
		                    }else if (annotation.getValue() instanceof IRI){
		                    	
		                    	IRI i = (IRI) annotation.getValue();
		                    	System.out.println(cls + " -> IRI: " + i.getIRIString());
		                    	System.out.println("AnnotProp: " + annotation.getAnnotationPropertiesInSignature());
	                            System.out.println("DataType:  " + annotation.getDatatypesInSignature());
			                    System.out.println("Signature: " + annotation.getSignature());
		                    	System.out.println();
		                    }
		                	
		                	/*
		                	System.out.println(annotation.getValue());
		                	
		                	System.out.println(annotation.annotationPropertiesInSignature());
		                	Stream <OWLAnnotation> annot = annotation.annotations();
		                	annot.forEach(a -> System.out.println(a));
		                	*/
		                }
		            }
		        }
		        
		        
		        
		        //=========================================================================================================================== PELLET
		        //Logger LOG = LoggerFactory.getLogger(MainFile5.class);
		       // ReasonerProgressMonitor progressMonitor = new LoggingReasonerProgressMonitor(LOG, "testDescendants");
		        
			   // new SimpleConfiguration();
			    //OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
			    //OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
			   // PelletReasoner pellet_reas = PelletReasonerFactory.getInstance().createReasoner( ontology,  config);
			    PelletReasoner pellet_reas = PelletReasonerFactory.getInstance().createNonBufferingReasoner(ontology);
			    // Ask the reasoner to do all the necessary work now, not later. OK.
			    pellet_reas.precomputeInferences();
			   // List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
	            //gens.add(new InferredSubClassAxiomGenerator());
			    InferredOntologyGenerator generator = new InferredOntologyGenerator( pellet_reas );
			    List<InferredAxiomGenerator<? extends OWLAxiom>> AGen = generator.getAxiomGenerators();
			    AGen.add(new InferredSubClassAxiomGenerator());
			   
			    OWLDataFactory man = OWLManager.getOWLDataFactory();
			    
			    OWLOntology infOnt = manager.createOntology();
			    InferredOntologyGenerator iog = new InferredOntologyGenerator(pellet_reas, AGen);
			    
			    iog.fillOntology(man, infOnt);
			    iog.fillOntology(man, ontology);
			    System.out.println("\n Inferred --- There are " + infOnt.getAxiomCount() + " axioms");
			    System.out.println("\n Total --- There are " + ontology.getAxiomCount() + " axioms");
			    
			    System.out.println(pellet_reas.getReasonerName() + " " + pellet_reas.getReasonerVersion());
			    if(pellet_reas.getPendingAxiomAdditions().isEmpty()) System.out.println("No Additions");
			    if(pellet_reas.getPendingAxiomRemovals().isEmpty()) System.out.println("No Removals");
			    List<OWLOntologyChange> lista = pellet_reas.getPendingChanges();
			    
			    int cnter = 0;
				for (OWLOntologyChange temp : lista) {
					System.out.println(cnter++ + " " + temp.toString());
				}
				
			    if(pellet_reas.getPendingChanges().isEmpty()) System.out.println("No Pending");

			    
			    //pellet_reas.
			    //==================================================================================================================================
		        
		        
		        
		        axiomas=infOnt.getAxioms();
		        System.out.println("Analisando axiomas adicionais");
		        it_axiomas=axiomas.iterator();
		        
		        cnt = 0;
		        
		        while(!it_axiomas.hasNext()) {//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% DEACTIVATED
		            OWLAxiom tempAx= it_axiomas.next();

	            	//OWLNamedIndividual ind = tempAx.getIndividualsInSignature().iterator().next();
	            	
		            
		            System.out.print(cnt + " " + tempAx.getAxiomType().getName());
		            if(tempAx.isLogicalAxiom()) System.out.println(" (É um axioma lógico)"); else System.out.println();
	                if(!tempAx.getIndividualsInSignature().isEmpty()) System.out.println("\n" + cnt +   " Individuo: " + tempAx.getIndividualsInSignature());
	                if(!tempAx.getDataPropertiesInSignature().isEmpty()) System.out.println(cnt +   " DataProp:  " + tempAx.getDataPropertiesInSignature());
	                if(!tempAx.getObjectPropertiesInSignature().isEmpty()) System.out.println(cnt + " ObjProp:   " + tempAx.getObjectPropertiesInSignature());
	                if(!tempAx.getAnnotationPropertiesInSignature().isEmpty()) System.out.println(cnt + " Anotacoes: " + tempAx.getAnnotationPropertiesInSignature());
	                if(!tempAx.getClassesInSignature().isEmpty()) System.out.println(cnt + " Classes:   " + tempAx.getClassesInSignature());
	                if(!tempAx.getDatatypesInSignature().isEmpty()) System.out.println(cnt + " Datatypes: " + tempAx.getDatatypesInSignature());
	                if(!tempAx.getSignature().isEmpty()) System.out.println(cnt + " Signature: " + tempAx.getSignature());
	                System.out.println();
	                cnt++;

		        }
		        
		        //=============================================================== now print all axioms
		        
		        axiomas = ontology.getAxioms();		        
		        System.out.println("Analisando axiomas totais");
		        it_axiomas=axiomas.iterator();
		        
		        cnt = 0;
		        
		        while(!it_axiomas.hasNext()) {
		            OWLAxiom tempAx= it_axiomas.next();

	            	//OWLNamedIndividual ind = tempAx.getIndividualsInSignature().iterator().next();
	            	
		            
		            System.out.print(cnt + " " + tempAx.getAxiomType().getName());
		            if(tempAx.isLogicalAxiom()) System.out.println(" (É um axioma lógico)"); else System.out.println();
	                if(!tempAx.getIndividualsInSignature().isEmpty()) System.out.println("\n" + cnt +   " Individuo: " + tempAx.getIndividualsInSignature());
	                if(!tempAx.getDataPropertiesInSignature().isEmpty()) System.out.println(cnt +   " DataProp:  " + tempAx.getDataPropertiesInSignature());
	                if(!tempAx.getObjectPropertiesInSignature().isEmpty()) System.out.println(cnt + " ObjProp:   " + tempAx.getObjectPropertiesInSignature());
	                if(!tempAx.getAnnotationPropertiesInSignature().isEmpty()) System.out.println(cnt + " Anotacoes: " + tempAx.getAnnotationPropertiesInSignature());
	                if(!tempAx.getClassesInSignature().isEmpty()) System.out.println(cnt + " Classes:   " + tempAx.getClassesInSignature());
	                if(!tempAx.getDatatypesInSignature().isEmpty()) System.out.println(cnt + " Datatypes: " + tempAx.getDatatypesInSignature());
	                if(!tempAx.getSignature().isEmpty()) System.out.println(cnt + " Signature: " + tempAx.getSignature());
	                
	                System.out.println();
	                cnt++;

		        }
		        
		        //==================================================================================== works with annotations
		        // We want to add a comment to the Quokka class. First, we need to obtain a reference to the class

		   
		
		
		        System.out.println("Anotacoes");
		        Set<OWLAnnotation> annot = ontology.getAnnotations();
		        System.out.println(ontology. annotationPropertiesInSignature());
		        Iterator<OWLAnnotation> it_annot = annot.iterator();
		        
		        cnt = 0;
		        while(it_annot.hasNext()) {
		        	OWLAnnotation tempAn = it_annot.next();

	            	//OWLNamedIndividual ind = tempAx.getIndividualsInSignature().iterator().next();
	            	
		        	System.out.println(cnt + " is Iri: " + tempAn.isIRI());
		        	System.out.println(tempAn.getClassesInSignature());
		        	System.out.println(tempAn.getIndividualsInSignature());
		        	System.out.println(tempAn.getAnnotationPropertiesInSignature());
		        	System.out.println(tempAn.getAnnotations());
		        	System.out.println(tempAn.getSignature());

	                System.out.println();
	                cnt++;

		        }
			    
			    
		        /*    private void printProperties(@Nonnull OWLOntology o, @Nonnull OWLReasoner reasoner, OWLClass cls) {
        for (OWLObjectPropertyExpression prop : o.getObjectPropertiesInSignature()) {
            assert prop != null;
            // To test whether an instance of A MUST have a property p with a
            // filler, check for the satisfiability of A and not (some p Thing)
            // if this is satisfiable, then there might be instances with no
            // p-filler
            OWLClassExpression restriction = df.getOWLObjectSomeValuesFrom(prop, df.getOWLThing());
            OWLClassExpression intersection = df.getOWLObjectIntersectionOf(cls, df.getOWLObjectComplementOf(
                restriction));
            boolean sat = !reasoner.isSatisfiable(intersection);
            if (sat) {
                assertNotNull(prop);
                // System.out.println("Instances of " + cls +
                // " necessarily have the property " + prop);
            }
        }
}*/
		        
		        System.out.println("\nDirect Subclasses of Process:");
		        OWLDataFactory fac = manager.getOWLDataFactory();
		        OWLClass taco = fac.getOWLClass(/*IRI.create(ToppingsClassIRI)*/ IRI.create(ontology.getOntologyID().getOntologyIRI().get() + "#Process"));
		        NodeSet<OWLClass> subTaco = reasoner.getSubClasses(taco, true);
		        Set<OWLClass> tacos = subTaco.getFlattened();
		        for (OWLClass cls : tacos) System.out.println(" " + cls);
		        
		        System.out.println("\nAll Subclasses of Process:");
		        NodeSet<OWLClass> subsubTaco = reasoner.getSubClasses(taco, false);
		        tacos = subsubTaco.getFlattened();
		        for (OWLClass cls : tacos) System.out.println(" " + cls);
		        
		        System.out.println("\nHierarchy of your classes:");
		        Node<OWLClass> topNode = reasoner.getTopClassNode();
		        print(topNode, reasoner, 0);
		        
				//Get an instance of the Protege Pellet reasoner
				//ProtegeReasoner reasoner = reasonerManager.createProtegeReasoner(owlModel, ProtegePelletJenaReasoner.class);
				
			    
			    //SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
			    // System.out.println("Engine Done");
			    
				System.out.println("Done!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private static void printClassAnnotations(OWLClass cls, OWLOntology ontology) {
	    for (OWLOntology o : ontology.getImportsClosure()) { //gets all related ontologies (imports)
	        for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(cls.getIRI())) { //gets annotations from every ontology file
	        	

            	System.out.println("\t"+"======================================================== has Annotation");
	        	
	        	IRI propNonInst = IRI.create(ontology.getOntologyID().getOntologyIRI().get() + "#NonInstantiable");
	        	
	        	Set<OWLAnnotationProperty> anotproplist = annotation.getAnnotationPropertiesInSignature();
	        	for (OWLAnnotationProperty ap : anotproplist) { //Percorre as anot props de cada anotacao (em principio so ha uma)
	        		if(propNonInst.compareTo(ap.getIRI()) == 0) 
	        			System.out.println("\t"+"Nao instanciavel!");
	        	    System.out.println("\t"+"Annot Prop:  " + ap + "  por extenso:  " + ap.getIRI());
	        	}
	        	
	            if (annotation.getValue() instanceof OWLLiteral) {
	            	
	            	System.out.println("\t"+"======================================================== has Literal Annotation");
	                OWLLiteral val = (OWLLiteral) annotation.getValue();
	                //if (val.hasLang("pt")) {
	                    System.out.println("\t"+cls + " -> Literal: " + val.getLiteral());
	                //}
	                    System.out.println("\t"+"AnnotProp: " + annotation.getAnnotationPropertiesInSignature());
	                    System.out.println("\t"+"DataType:  " + annotation.getDatatypesInSignature());
	                    System.out.println("\t"+"Signature: " + annotation.getSignature());
	                   System.out.println();
	            }else if (annotation.getValue() instanceof IRI){
                	System.out.println("\t"+"======================================================== has IRI Annotation");
	            	IRI i = (IRI) annotation.getValue();
	            	System.out.println(cls + " -> IRI: " + i.getIRIString());
	            	System.out.println("AnnotProp: " + annotation.getAnnotationPropertiesInSignature());
	                System.out.println("DataType:  " + annotation.getDatatypesInSignature());
	                System.out.println("Signature: " + annotation.getSignature());
	            	System.out.println();
	            }

            	System.out.println("\t"+"================================================================================");
	        }
	    }/*{if(s == null) return;}*/
	}
	
	private static boolean isClassInstantiable(OWLClass cls, OWLOntology ontology) {
		
		IRI propNonInst = IRI.create(ontology.getOntologyID().getOntologyIRI().get() + "#NonInstantiable");// AnnotationProperty IRI
		
		
		ontology.importsClosure().forEach(o -> { //  Gets all related ontologies (imports) [source: OWL API example]
			o.annotationAssertionAxioms(cls.getIRI()).forEach(a -> {
				//if(propNonInst.compareTo(a.annotationPropertiesInSignature().findAny().get().getIRI()) == 0) return false; //Gets the only AnnotationProperty of each annotation 
	        	/*for (OWLAnnotationProperty ap : annotation.getAnnotationPropertiesInSignature()) { //		  Gets all AnnotationProperties of each annotation 
	        		if(propNonInst.compareTo(ap.getIRI()) == 0) return false;
	        	}*/
			});
		});
		
		
		//POR RETURN VALUES DENTRO DE FOR EACH OU ENTAO ALTERAR VARIAVEIS GLOBAIS..ALTERAR ?????
		
		
	    for (OWLOntology o : ontology.getImportsClosure())  { //                                               Gets all related ontologies (imports)
	        for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(cls.getIRI())) { //  Gets annotations from every ontology file   
	        	if(propNonInst.compareTo(annotation.annotationPropertiesInSignature().findAny().get().getIRI()) == 0) return false; //Gets the only AnnotationProperty of each annotation 
	        	/*for (OWLAnnotationProperty ap : annotation.getAnnotationPropertiesInSignature()) { //		  Gets all AnnotationProperties of each annotation 
	        		if(propNonInst.compareTo(ap.getIRI()) == 0) return false;
	        	}*/
	        }
	    }
	    return true;
	    
	}
	
	 private static void print(@Nonnull Node<OWLClass> parent, @Nonnull OWLReasoner reasoner, int depth) {
	        // We don't want to print out the bottom node (containing owl:Nothing
	        // and unsatisfiable classes) because this would appear as a leaf node
	        // everywhere
	        if (parent.isBottomNode()) {
	            return;
	        }
	        // Print an indent to denote parent-child relationships
	        printIndent(depth);
	        // Now print the node (containing the child classes)
	        printNode(parent);
	        for (Node<OWLClass> child : reasoner.getSubClasses(parent.getRepresentativeElement(), true)) {
	            assert child != null;
	            // Recurse to do the children. Note that we don't have to worry
	            // about cycles as there are non in the inferred class hierarchy
	            // graph - a cycle gets collapsed into a single node since each
	            // class in the cycle is equivalent.
	            print(child, reasoner, depth + 1);
	        }
	    }

	    private static void printIndent(int depth) {
	        for (int i = 0; i < depth; i++) {
	            System.out.print("\t");
	        }
	    }

	    private static void printNode(@Nonnull Node<OWLClass> node) {
	        // The default prefix used here is only an example.
	        // For real ontologies, choose a meaningful prefix - the best
	        // choice depends on the actual ontology.
	        DefaultPrefixManager pm = new DefaultPrefixManager(null, null, "http://owl.man.ac.uk/2005/07/sssw/people#");
	        // Print out a node as a list of class names in curly brackets
	        for (Iterator<OWLClass> it = node.getEntities().iterator(); it.hasNext();) {
	            OWLClass cls = it.next();
	            // User a prefix manager to provide a slightly nicer shorter name
	            String shortForm = pm.getShortForm(cls);
	            System.out.println(shortForm);
	        }
	}
}