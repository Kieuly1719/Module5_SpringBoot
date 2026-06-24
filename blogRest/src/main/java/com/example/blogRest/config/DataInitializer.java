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

        blogRepository.save(new Blog(
                "Lam quen voi Java",
                "Nhung khai niem co ban khi bat dau hoc Java.",
                "Java la ngon ngu lap trinh huong doi tuong, duoc su dung pho bien trong lap trinh web va enterprise.",
                java
        ));
        blogRepository.save(new Blog(
                "Tao ung dung Spring Boot dau tien",
                "Cach tao project Spring Boot va chay ung dung local.",
                "Spring Boot giup tao ung dung Java nhanh hon bang cach cau hinh san nhieu thanh phan pho bien.",
                springBoot
        ));
        blogRepository.save(new Blog(
                "RESTful API trong ung dung Blog",
                "Bo sung API de lay danh sach category, danh sach bai viet va chi tiet bai viet.",
                "RESTful API tra ve du lieu JSON, phu hop cho Postman, frontend rieng hoac ung dung mobile.",
                restfulApi
        ));
    }
}
