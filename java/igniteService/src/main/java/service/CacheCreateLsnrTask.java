package service;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;
import org.apache.ignite.resources.IgniteInstanceResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheCreateLsnrTask extends ComputeTaskAdapter<String, Integer> {

    private static final long serialVersionUID = 3L;
    private Ignite ignite;

    @IgniteInstanceResource
    public void setIgnite(Ignite ignite) {
        this.ignite = ignite;
        System.out.println("inside setIgnite");
        if (CacheCreateLsnr.counter == null){
            CacheCreateLsnr.init(ignite);
        }
    }

    @Override
    public Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> nodes, String arg) {
        Map<ComputeJob, ClusterNode> map = new HashMap<>();

        map.put(new ComputeJobAdapter() {
                @Override
                public Object execute() {
            //System.out.println("CacheCreateLsnr.counter - " + CacheCreateLsnr.counter.getI());
            return "";
                }
            }, nodes.get(0));
        return map;
    }

    @Override
    public Integer reduce(List<ComputeJobResult> results) {
//        int sum = 0;
//        for (ComputeJobResult res : results)
//            sum += res.<Integer> getData();
        return 0;
    }
}
