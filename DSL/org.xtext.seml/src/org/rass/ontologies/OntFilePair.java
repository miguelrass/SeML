package org.rass.ontologies;

import org.semanticweb.owlapi.model.IRI;

class OntFilePair {

	  private final long date;
	  private final IRI iri;

	  public OntFilePair(long d, IRI i) {
	    this.date = d;
	    this.iri = i;
	  }

	  public long getDate() { return date; }
	  public IRI getIRI() { return iri; }

	  //@Override
	  //public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (!(o instanceof OntFilePair)) return false;
	    OntFilePair pairo = (OntFilePair) o;
	    return this.date == pairo.getDate() && this.iri.equals(pairo.getIRI());
	  }
	
}