/*
 * generated by Xtext 2.10.0
 */
package org.xtext.seml


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class SeMLStandaloneSetup extends SeMLStandaloneSetupGenerated {

	def static void doSetup() {
		new SeMLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
