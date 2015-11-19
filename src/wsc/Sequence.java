package wsc;

import java.util.Set;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Sequence extends GPNode {

	@Override
	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack, final GPIndividual individual, final Problem problem) {
		WSCInitializer init = (WSCInitializer) state.initializer;
		double[] qos;
		Set<String> inputs;
		Set<String> outputs;
		int totalInputs;
		int satisfiedInputs;

		WSCData rd = ((WSCData) (input));

		children[0].eval(state, thread, input, stack, individual, problem);
		qos = rd.qos;
		inputs = rd.inputs;
		outputs = rd.outputs;
		totalInputs = rd.totalInputs;
		satisfiedInputs = rd.satisfiedInputs;

		children[1].eval(state, thread, input, stack, individual, problem);
		rd.qos[WSCInitializer.TIME] += qos[WSCInitializer.TIME];
		rd.qos[WSCInitializer.COST] += qos[WSCInitializer.COST];
		rd.qos[WSCInitializer.AVAILABILITY] *= rd.qos[WSCInitializer.AVAILABILITY];
		rd.qos[WSCInitializer.RELIABILITY] *= rd.qos[WSCInitializer.RELIABILITY];

		rd.totalInputs += rd.totalInputs; // Add the inputs of the right-hand side
		rd.totalInputs += totalInputs;
		rd.satisfiedInputs += satisfiedInputs;
		rd.satisfiedInputs += init.countInputsSatisfied(outputs, rd.inputs); // We only count the inputs of the right-hand side
		rd.inputs = inputs; // Keep inputs from left child only
		rd.outputs.addAll(outputs); // Outputs from left and right children
	}

	@Override
	public String toString() {
		return "Sequence";
	}

	@Override
	public int expectedChildren() {
		return 2;
	}
}
