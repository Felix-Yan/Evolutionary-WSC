package wsc;

import java.util.HashSet;
import java.util.Set;

import ec.util.*;
import ec.*;
import ec.gp.*;

public class WSCData extends GPData {
	public double[] qos;
	public Set<String> inputs = new HashSet<String>();
	public Set<String> outputs = new HashSet<String>();
	public int totalInputs;
	public int satisfiedInputs;

	public void copyTo(final GPData gpd) {
		WSCData wscd = (WSCData) gpd;
		wscd.qos = qos;
		wscd.inputs = inputs;
		wscd.outputs = outputs;
		wscd.totalInputs = totalInputs;
		wscd.satisfiedInputs = satisfiedInputs;
	}
}
