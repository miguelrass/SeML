package org.semanticweb.owlapi.util;


import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.util.OWLObjectVisitorAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class OWLObjectVisitor extends OWLObjectVisitorAdapter{

    //private static final Logger logger = LoggerFactory.getLogger(OWLObjectVisitor.class);
    
    @Override
    public void visit(OWLDataMaxCardinality desc) {
        super.visit(desc);
        //logger.debug("Max Cardinality =["+desc.getCardinality()+"]");
        System.out.println("Max Cardinality =["+desc.getCardinality()+"]");
    }

    @Override
    public void visit(OWLDataMinCardinality desc) {
        super.visit(desc);
        //logger.debug("Min Cardinality =["+desc.getCardinality()+"]");
        System.out.println("Min Cardinality =["+desc.getCardinality()+"]");
    }

    @Override
    public void visit(OWLObjectMaxCardinality desc) {
        super.visit(desc);
        //logger.debug("Object Max Cardinality =["+desc.getCardinality()+"]");
        System.out.println("Object Max Cardinality =["+desc.getCardinality()+"]");
    }

    @Override
    public void visit(OWLObjectMinCardinality desc) {
        super.visit(desc);
        //logger.debug("Object Min Cardinality =["+desc.getCardinality()+"]");
        System.out.println("Object Min Cardinality =["+desc.getCardinality()+"]");
    }

    @Override
    public void visit(OWLDataExactCardinality desc) {
        super.visit(desc);
        //logger.debug("Exact Data Cardinality =["+desc.getCardinality()+"]");
        System.out.println("Exact Data Cardinality =["+desc.getCardinality()+"]");
    }

    @Override
    public void visit(OWLObjectExactCardinality desc) {
        super.visit(desc);
        //logger.debug("Exact Object Cardinality =["+desc.getCardinality()+"]");
        System.out.println("Exact Object Cardinality =["+desc.getCardinality()+"]");
    }

}