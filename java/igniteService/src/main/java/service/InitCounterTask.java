package service;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;
import org.apache.ignite.resources.IgniteInstanceResource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InitCounterTask extends ComputeTaskAdapter<String, Integer> {

    private static final long serialVersionUID = 1L;

    @IgniteInstanceResource
    public void setIgnite(Ignite ignite) {
        System.out.println("inside setIgnite");
        if (Counter.Holder.instance==null){
            System.out.println("Counter.init()");
            Counter.init();
        }
    }

    @Override
    public Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> nodes, String arg) {
        Map<ComputeJob, ClusterNode> map = new HashMap<>();

        Iterator<ClusterNode> it = nodes.iterator();

        for (final String word : arg.split(" ")) {
            // If we used all nodes, restart the iterator.
            if (!it.hasNext())
                it = nodes.iterator();

            ClusterNode node = it.next();

            map.put(new ComputeJobAdapter() {
                @Override
                public Object execute() {
                    System.out.println(">>> Printing '" + word + "' on this node from ignite job.");

                    if(Counter.Holder.instance!=null){
                        System.out.println("counter - " + Counter.Holder.instance.getx());
                    }

                    // Return number of letters in the word.
                    return word.length();
                }
            }, node);
        }

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
