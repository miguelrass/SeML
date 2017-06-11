package ex_swrl;

import java.util.Set;
import org.mindswap.pellet.ABox;
import org.mindswap.pellet.Node;
import org.mindswap.pellet.utils.ATermUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.rass.swrl.CustomSWRLBuiltin;
import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.rules.builtins.BuiltInRegistry;

/**
* This class checks if... (write description)
*
* @param arguments  example:(value, individual, objectproperty, [targetClass])
* @return true if...
*/
public class twoInst implements CustomSWRLBuiltin.CustomSWRLFunction {
	private static final String ARGerr = "SWRL Built-in <twoInst> Error: wrong arguments!";
	
	static{
		BuiltInRegistry.instance.registerBuiltIn("esrg:basis#twoInst", new CustomSWRLBuiltin(new twoInst()));
		System.out.println("\"twoInst\" built-in was registered!");
	}
	
	@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) { System.out.print('.');
	
		if((args.length != 1 || !args[0].isLiteral())){
			System.out.println(ARGerr); return false;
		}
		
		// TODO: built-in code
		
		return true;
	}
	
	@Override public boolean isApplicable(boolean[] boundPositions) {
		return ((boundPositions.length == 1 && boundPositions[0]));
	}
}
