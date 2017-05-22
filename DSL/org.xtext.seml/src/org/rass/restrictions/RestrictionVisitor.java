package org.rass.restrictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

import uk.ac.manchester.cs.owl.owlapi.OWLObjectSomeValuesFromImpl;

/**
 * Deprecated
 * @author Miguel
 *
 */
public class RestrictionVisitor {

        private final Set<OWLClass> processedClasses = new HashSet<OWLClass>();
        private final OWLOntology ont;
        private HashSet<OWLClassExpression> Restrictions = new HashSet<OWLClassExpression>();

        public RestrictionVisitor(OWLOntology ont) {
            this.ont = ont;
        }
        
        public HashSet<OWLClassExpression> getRestrictions(){
        	return Restrictions;
        }

        public void visit(OWLClassExpression ce) {
        	
        	// OWLClassExpression might be a Named Class or Anonymous Class [restriction]
        	//---------------------------------------------------------------------------
        	// 1st (if it is a Class) continue ontology traversal 
        	if(ce instanceof OWLClass){
        		OWLClass c = (OWLClass) ce;
        		
        		if (!processedClasses.contains(c)) {
	                // Processing inherited restrictions - recursively visit super classes
	                // Keep track of processed classes to avoid getting caught out by cycles in the taxonomy
	                processedClasses.add(c);
	                
	                //Get all super classes (Named Classes and Anonymous [restrictions])
                    for (OWLSubClassOfAxiom ax : ont.getSubClassAxiomsForSubClass(c)) {
                    	visit(ax.getSuperClass());
                    }

        		}
        	// 2nd (if it is not a Class)
        	}else{
        		Restrictions.add(ce); //Add restriction
        	}
        }
        
        //public void visit(OWLObjectSomeValuesFrom ce) {
            // This method gets called when a class expression is an existential
            // (someValuesFrom) restriction and it asks us to visit it
        //}
}