package service;

import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ComputeTask2 extends ComputeTaskAdapter<String, Integer> {

    private static final long serialVersionUID = 2L;

    @Override
    public Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> nodes, String arg) {
        Map<ComputeJob, ClusterNode> map = new HashMap<>();

            map.put(new ComputeJobAdapter() {
                @Override
                public Object execute() {

                    if (CounterHolder.counter == null){
                        CounterHolder.counter = new Counter();
                    }

                    CounterHolder.counter.incrementI();
                    System.out.println(CounterHolder.counter.getI());

                    return 0;
                }
            }, nodes.get(0));

        return map;
    }

    @Override
    public Integer reduce(List<ComputeJobResult> results) {
        int sum = 0;

        for (ComputeJobResult res : results)
            sum += res.<Integer> getData();

        return sum;
    }
}
