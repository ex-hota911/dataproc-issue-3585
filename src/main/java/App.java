import com.google.cloud.dataproc.v1beta2.Cluster;
import com.google.cloud.dataproc.v1beta2.ClusterConfig;
import com.google.cloud.dataproc.v1beta2.ClusterControllerClient;
import com.google.cloud.dataproc.v1beta2.CreateClusterRequest;
import com.google.cloud.dataproc.v1beta2.DeleteClusterRequest;
import com.google.cloud.dataproc.v1beta2.GceClusterConfig;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * This Java source file was generated by the Gradle 'init' task.
 *
 * <p>Usage:
 *
 * <pre>
 *  $ export GOOGLE_APPLICATION_CREDENTIALS=/path/to/credential.json
 *  $ ./gradlew run --args "mercari-data-infra-prod test2"
 * </pre>
 */
public class App {

  public static void main(String[] args) throws IOException {
    String projectId = args[0];
    String clusterName = args[1];

    ClusterControllerClient clusterControllerClient = ClusterControllerClient.create();
    try {
      CreateClusterRequest request =
          CreateClusterRequest.newBuilder()
              .setProjectId(projectId)
              .setRegion("global")
              .setCluster(
                  Cluster.newBuilder()
                      .setClusterName(clusterName)
                      .setConfig(
                          ClusterConfig.newBuilder()
                              .setGceClusterConfig(
                                  GceClusterConfig.newBuilder().setZoneUri("us-west1-a"))))
              .build();
      clusterControllerClient.createClusterAsync(request).get();

    } catch (InterruptedException | ExecutionException e) {
      System.err.println("An exception was thrown while creating a cluster");
      e.printStackTrace();
    } finally {
      DeleteClusterRequest request =
          DeleteClusterRequest.newBuilder()
              .setProjectId(projectId)
              .setRegion("global")
              .setClusterName(clusterName)
              .build();
      try {
        clusterControllerClient.deleteClusterAsync(request).get();
      } catch (InterruptedException | ExecutionException e) {
        System.err.println("An exception was thrown while deleting a cluster");
        e.printStackTrace();
      }
    }
  }
}
