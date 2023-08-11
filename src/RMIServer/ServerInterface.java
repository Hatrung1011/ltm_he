package RMIServer;

import Model.Stationery;
import Model.StationeryType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {
    public ArrayList<StationeryType> getAllType() throws RemoteException;

    public void addStationeryType(StationeryType type) throws RemoteException;

    public ArrayList<StationeryType> searchType(String type) throws RemoteException;

    public void updateType(StationeryType type) throws RemoteException;

    public void deleteType(int id) throws RemoteException;

    public ArrayList<Stationery> getAllStationery() throws RemoteException;

    public ArrayList<Stationery> getStationeryByName(String name) throws RemoteException;

    public ArrayList<Stationery> getStationeryByType(String type) throws RemoteException;

    public ArrayList<Stationery> getStationeryByPrice(String price) throws RemoteException;

    public void addStationery(Stationery stationery) throws RemoteException;

    public void updateStationery(Stationery stationery) throws RemoteException;

    public void deleteStationery(int id) throws RemoteException;

}
