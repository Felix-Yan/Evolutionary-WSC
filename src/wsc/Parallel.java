package wsc;

import java.util.Set;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Parallel extends GPNode {

	@Override
	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack, final GPIndividual individual, final Problem problem) {
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
		rd.qos[WSCInitializer.TIME] = Math.max(rd.qos[WSCInitializer.TIME], qos[WSCInitializer.TIME]);
		rd.qos[WSCInitializer.COST] += qos[WSCInitializer.COST];
		rd.qos[WSCInitializer.AVAILABILITY] *= rd.qos[WSCInitializer.AVAILABILITY];
		rd.qos[WSCInitializer.RELIABILITY] *= rd.qos[WSCInitializer.RELIABILITY];
		rd.inputs.addAll(inputs);
		rd.outputs.addAll(outputs);
		rd.totalInputs += totalInputs;
		rd.satisfiedInputs += satisfiedInputs;
	}

	@Override
	public String toString() {
		return "Parallel";
	}

	@Override
	public int expectedChildren() {
		return 2;
	}
}
