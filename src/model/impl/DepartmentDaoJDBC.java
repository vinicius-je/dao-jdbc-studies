package model.impl;

import database.DB;
import database.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn = null;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "INSERT INTO department (Name) " +
                        "VALUES (?);",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, department.getName());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? "+
                            "WHERE Id = ?");

            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "DELETE FROM department " +
                        "WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Department dep = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT * FROM department " +
                    "WHERE id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()){
                dep = new Department(rs.getInt("Id"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

        return dep;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> departmentList = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT *  FROM department");
            rs = ps.executeQuery();

            while (rs.next()){
                Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
                departmentList.add(dep);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

        return departmentList;
    }
}
