package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Runner {
    public static void main(String[] args) throws Exception {
        Server rmiServer = new Server();
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/RMIServer", rmiServer);
        System.out.println("server running");
    }
}
