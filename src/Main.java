import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDAO();

        List<Department> departmentList = departmentDao.findAll();
        for (Department dep : departmentList) {
            System.out.println(dep);
        }

        System.out.println(departmentDao.findById(3));

//        Department dep = new Department(null, "TECH");
//        departmentDao.insert(dep);
//        System.out.println(dep);

//        departmentDao.deleteById(6);

    }
}