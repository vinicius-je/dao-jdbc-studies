package model.dao;

import database.DB;
import model.impl.DepartmentDaoJDBC;
import model.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDao createDepartmentDAO(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
