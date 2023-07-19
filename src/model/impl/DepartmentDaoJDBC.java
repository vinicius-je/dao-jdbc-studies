package model.impl;

import database.DB;
import database.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn = null;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        Statement st = null;
        ResultSet rs = null;
        List<Department> departmentList = new ArrayList<>();

        try {
            st = conn.createStatement();

            rs = st.executeQuery("SELECT *  FROM department");

            while (rs.next()){
                Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
                departmentList.add(dep);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return departmentList;
    }
}
