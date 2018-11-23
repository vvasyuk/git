package service;

import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseCounterTask extends ComputeTaskAdapter<String, Integer> {

    private static final long serialVersionUID = 2L;

    @Override
    public Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> nodes, String arg) {
        Map<ComputeJob, ClusterNode> map = new HashMap<>();

            map.put(new ComputeJobAdapter() {
                @Override
                public Object execute() {

                    if(Counter.Holder.instance == null){
                        Counter.init();
                    }
                    Counter.Holder.instance.incrementx();
                    System.out.println("counter - " + Counter.Holder.instance.getx());

                    return 0;
                }
            }, nodes.get(0));

        return map;
    }

    @Override
    public Integer reduce(List<ComputeJobResult> results) {
        return 0;
    }
}
