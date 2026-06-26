package com.example.blogRest.config;

import com.example.blogRest.model.Blog;
import com.example.blogRest.model.Category;
import com.example.blogRest.repository.BlogRepository;
import com.example.blogRest.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;

    public DataInitializer(CategoryRepository categoryRepository, BlogRepository blogRepository) {
        this.categoryRepository = categoryRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            return;
        }

        Category java = categoryRepository.save(new Category("Java"));
        Category springBoot = categoryRepository.save(new Category("Spring Boot"));
        Category restfulApi = categoryRepository.save(new Category("RESTful API"));

        saveBlogs(java, new String[][]{
                {"Lam quen voi Java", "Nhung khai niem co ban khi bat dau hoc Java.", "Java la ngon ngu lap trinh huong doi tuong, duoc su dung pho bien trong lap trinh web va enterprise."},
                {"Bien va kieu du lieu trong Java", "Tong quan ve bien, hang va cac kieu du lieu thuong gap.", "Nam chac kieu du lieu giup chuong trinh ro rang hon va tranh loi khi xu ly so, chuoi, ngay thang."},
                {"Vong lap va cau dieu kien", "Ung dung if, switch, for va while trong bai tap Java.", "Cac cau truc dieu khien giup lap trinh vien mo ta luong xu ly va lap lai tac vu mot cach ngan gon."},
                {"Lap trinh huong doi tuong", "Class, object, constructor va tinh dong goi.", "OOP la nen tang quan trong de tach doi tuong nghiep vu va tai su dung code trong ung dung lon."},
                {"Ke thua va da hinh", "Cach mo rong lop va ghi de phuong thuc.", "Ke thua giup chia se hanh vi chung, con da hinh giup code linh hoat khi lam viec voi nhieu kieu doi tuong."},
                {"Xu ly ngoai le trong Java", "Try-catch-finally va cach tao custom exception.", "Xu ly ngoai le dung cach giup ung dung phan hoi tot hon khi gap dau vao khong hop le hoac loi he thong."},
                {"Collection Framework co ban", "List, Set, Map va cach chon cau truc du lieu.", "Collection Framework cung cap nhieu kieu luu tru phu hop voi nhu cau sap xep, tim kiem va loai bo trung lap."},
                {"Lam viec voi Stream API", "Filter, map, sorted va collect trong Java.", "Stream API giup thao tac danh sach theo phong cach khai bao, code ngan gon va de doc hon."}
        });

        saveBlogs(springBoot, new String[][]{
                {"Tao ung dung Spring Boot dau tien", "Cach tao project Spring Boot va chay ung dung local.", "Spring Boot giup tao ung dung Java nhanh hon bang cach cau hinh san nhieu thanh phan pho bien."},
                {"Cau truc project Spring Boot", "Tim hieu controller, service, repository va resources.", "Mot cau truc ro rang giup tach trach nhiem giua cac tang va de bao tri khi ung dung phat trien."},
                {"Spring MVC va Thymeleaf", "Ket hop controller voi template de hien thi giao dien.", "Thymeleaf phu hop voi cac bai tap MVC vi co the render HTML truc tiep tu model cua Spring."},
                {"Spring Data JPA co ban", "Tao repository va truy van du lieu voi JpaRepository.", "Spring Data JPA giam nhieu boilerplate khi can them, sua, xoa va tim kiem du lieu trong database."},
                {"Validation trong form", "Su dung annotation de rang buoc du lieu dau vao.", "Bean Validation giup ung dung bao loi som va hien thi thong diep than thien tren giao dien."},
                {"Ket noi H2 Database", "Dung H2 de chay demo khong can cai dat MySQL.", "H2 phu hop cho bai tap vi khoi dong nhanh, du lieu mau duoc tao lai moi lan chay ung dung."},
                {"Tach lop service trong ung dung Blog", "Dua nghiep vu ra khoi controller.", "Tang service giup controller gon hon va giu code xu ly co the tai su dung trong nhieu endpoint."},
                {"Sap xep bai viet moi nhat", "Hien thi bai viet theo ngay tao giam dan.", "Danh sach blog thuong can bai moi nhat nam tren dau de nguoi dung thay noi dung cap nhat truoc."},
                {"Phan trang voi Spring Data", "Dung Pageable va Page de chia nho danh sach.", "Phan trang giup giao dien tai nhanh hon va server khong phai tra ve qua nhieu ban ghi cung luc."}
        });

        saveBlogs(restfulApi, new String[][]{
                {"RESTful API trong ung dung Blog", "Bo sung API de lay danh sach category, danh sach bai viet va chi tiet bai viet.", "RESTful API tra ve du lieu JSON, phu hop cho Postman, frontend rieng hoac ung dung mobile."},
                {"DTO trong REST API", "An bot entity va chi tra du lieu can thiet.", "DTO giup API on dinh hon, tranh lo du lieu noi bo va giam loi khi entity co quan he lazy loading."},
                {"ResponseEntity va ma trang thai", "Tra ve 200, 404 va cac response phu hop.", "HTTP status ro rang giup frontend biet request thanh cong hay can hien thi thong bao loi."},
                {"AJAX voi jQuery", "Goi API JSON ma khong tai lai toan bo trang.", "AJAX tao cam giac ung dung muot hon vi chi cap nhat vung danh sach blog khi nguoi dung tim kiem."},
                {"Tim kiem blog bang keyword", "Loc bai viet theo title, summary va content.", "Chuc nang tim kiem giup nguoi dung nhanh chong tim bai viet lien quan den chu de dang quan tam."},
                {"Nut Tai them voi AJAX", "Lay trang du lieu tiep theo va noi vao danh sach hien tai.", "Tai them la cach phan trang than thien, phu hop voi danh sach bai viet dai tren giao dien web."},
                {"Kiem thu REST endpoint", "Dung test de dam bao API tra ve du lieu dung.", "Kiem thu endpoint giup phat hien som loi route, loi JSON va loi khoi tao du lieu mau."},
                {"Xu ly trang thai rong", "Thong bao khi khong co ket qua tim kiem.", "Trang thai rong giup nguoi dung hieu ung dung van hoat dong dung, chi la khong tim thay bai phu hop."}
        });
    }

    private void saveBlogs(Category category, String[][] blogData) {
        for (String[] item : blogData) {
            blogRepository.save(new Blog(item[0], item[1], item[2], category));
        }
    }
}
