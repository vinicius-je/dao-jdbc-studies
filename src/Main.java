import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("#### TEST 1: seller findById ####");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n#### TEST 1: seller findById ####");
        Department department = new Department(2, null);
        List<Seller> sellerList = sellerDao.findByDepartment(department);
        for (Seller sell : sellerList) {
            System.out.println(sell);
        }

        System.out.println("\n#### TEST 3: seller findAll ####");
        sellerList = sellerDao.findAll();
        for (Seller sell : sellerList) {
            System.out.println(sell);
        }
    }
}