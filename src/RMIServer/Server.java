package RMIServer;

import DAO.DAO;
import Model.Stationery;
import Model.StationeryType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface {
    private DAO dao;

    public Server() throws RemoteException {
        super();
        this.dao = new DAO();
    }

    @Override
    public ArrayList<StationeryType> getAllType() throws RemoteException {
        return this.dao.getAllType();
    }

    @Override
    public void addStationeryType(StationeryType type) throws RemoteException {
        this.dao.addStationeryType(type);
    }

    @Override
    public ArrayList<StationeryType> searchType(String type) throws RemoteException {
        return this.dao.searchType(type);
    }

    @Override
    public void updateType(StationeryType type) throws RemoteException {
        this.dao.updateType(type);
    }

    @Override
    public void deleteType(int id) throws RemoteException {
        this.dao.deleteType(id);
    }

    @Override
    public ArrayList<Stationery> getAllStationery() throws RemoteException {
        return this.dao.getAllStationery();
    }

    @Override
    public ArrayList<Stationery> getStationeryByName(String name) throws RemoteException {
        return this.dao.getStationeryByName(name);
    }

    @Override
    public ArrayList<Stationery> getStationeryByType(String type) throws RemoteException {
        return this.dao.getStationeryByType(type);
    }

    @Override
    public ArrayList<Stationery> getStationeryByPrice(String price) throws RemoteException {
        return this.dao.getStationeryByPrice(price);
    }

    @Override
    public void addStationery(Stationery stationery) throws RemoteException {
        this.dao.addStationery(stationery);
    }

    @Override
    public void updateStationery(Stationery stationery) throws RemoteException {
        this.dao.updateStationery(stationery);
    }

    @Override
    public void deleteStationery(int id) throws RemoteException {
        this.dao.deleteStationery(id);
    }
}
