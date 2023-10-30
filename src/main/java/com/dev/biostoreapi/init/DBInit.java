package com.dev.biostoreapi.init;

import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.repository.SubcategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public DBInit(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count() == 0) {
            initBio();
            initHandmade();
        }

        if(subcategoryRepository.count() == 0) {
            initCosmetics();
            initClothes();
            initFruits();
            initVegetables();
            initHoney();
            initHandbags();
            initOthers();
            initOthersBio();
        }

    }

    private void initBio() {
        initCategory(MainCategoryNameEnum.BIO,
                "In our store, the bio products category offers sustainable and eco-friendly items derived " +
                        "from natural sources for health-conscious and environmentally aware customers.",
                "https://cdn.create.vista.com/api/media/small/633438938/stock-photo-female-consumer-choosing-organic-bio-products-buy-local-farmers-market"
        );

    }
    private void initHandmade() {
        initCategory(MainCategoryNameEnum.HANDMADE,
                "Unique and carefully crafted items, each infused with a personal touch and creative spirit.",
                "https://cdn.create.vista.com/api/media/small/45743443/stock-photo-handmade-soap-with-cinnamon-and-coffee");

    }


//    init Subcategories
    private void initCosmetics() {
        initSubcategory(SubCategoryNameEnum.COSMETICS,
                "Discover a range of beauty products crafted to enhance your natural radiance while prioritizing high-quality, safe ingredients.",
                "https://cdn.create.vista.com/api/media/small/45743443/stock-photo-handmade-soap-with-cinnamon-and-coffee",
                MainCategoryNameEnum.BIO
                );
    }

    private void initClothes() {
        initSubcategory(SubCategoryNameEnum.CLOTHES,
                "A diverse selection of stylish and sustainable attire, perfect for expressing your individuality while caring for the planet.",
                "https://thumbs.dreamstime.com/b/tie-dye-shirts-fabric-hanging-hangers-shop-handmade-94051356.jpg",
                MainCategoryNameEnum.HANDMADE
        );
    }

    private void initHoney() {
        initSubcategory(SubCategoryNameEnum.HONEY,
                "Savor nature's golden elixir with a variety of pure, delicious, and locally sourced honey products.",
                "https://as1.ftcdn.net/v2/jpg/02/22/61/34/1000_F_222613483_GX8vpz0e9qgLkkZUtQQwUtOQBRyhxku4.jpg",
                MainCategoryNameEnum.BIO
        );
    }

    private void initFruits() {
        initSubcategory(SubCategoryNameEnum.FRUITS,
                "Explore the best in fresh, organic fruits in our bio category, offering delectable options that are not only flavorful but also sourced with a commitment to sustainability and environmental well-being.",
                "https://media.istockphoto.com/id/995518546/photo/assortment-of-colorful-ripe-tropical-fruits-top-view.jpg?b=1&s=612x612&w=0&k=20&c=2eoyleu37cY-6Wd22JwFZ-jdhz9pqUSNkl48ByAzR_U=",
                MainCategoryNameEnum.BIO
        );
    }

    private void initVegetables() {
        initSubcategory(SubCategoryNameEnum.VEGETABLES,
                "Experience the nostalgia and wholesomeness of vegetables from Grandma's garden, where time-honored traditions yield the freshest and most lovingly cultivated produce.",
                "https://media.istockphoto.com/id/1203599923/photo/food-background-with-assortment-of-fresh-organic-vegetables.jpg?s=612x612&w=0&k=20&c=DZy1JMfUBkllwiq1Fm_LXtxA4DMDnExuF40jD8u9Z0Q=",
                MainCategoryNameEnum.BIO
        );
    }

    private void initHandbags() {
        initSubcategory(SubCategoryNameEnum.HANDBAGS,
                "Discover a world of unique style and craftsmanship in our handmade handbags category, where each bag tells a story of creativity and individuality.",
                "https://st.depositphotos.com/1735158/4843/i/450/depositphotos_48437439-stock-photo-brown-leather-bag-in-female.jpg",
                MainCategoryNameEnum.HANDMADE
        );
    }

    private void initOthers() {
        initSubcategory(SubCategoryNameEnum.OTHERS,
                "In our 'Others' category, you'll find a diverse array of unique and specialty items that don't quite fit into traditional categories, offering a delightful assortment of unexpected treasures.",
                "https://cdn.create.vista.com/api/media/small/161892326/stock-photo-dress-made-of-buttons-and-tailoring-items",
                MainCategoryNameEnum.HANDMADE
        );
    }

    private void initOthersBio() {
        initSubcategory(SubCategoryNameEnum.OTHERS,
                "In our 'Others' category, you'll find a diverse array of unique and specialty items that don't quite fit into traditional categories, offering a delightful assortment of unexpected treasures.",
                "https://cdn.create.vista.com/api/media/small/161892326/stock-photo-dress-made-of-buttons-and-tailoring-items",
                MainCategoryNameEnum.BIO
        );
    }





    private void initCategory(MainCategoryNameEnum name, String description, String imgUrl) {
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        category.setDescription(description);
        category.setImgUrl(imgUrl);
        categoryRepository.save(category);
    }

    private void initSubcategory(SubCategoryNameEnum name, String description, String imgUrl, MainCategoryNameEnum categoryName) {
        SubcategoryEntity subcategory = new SubcategoryEntity();
        subcategory.setName(name);
        subcategory.setDescription(description);
        subcategory.setImgUrl(imgUrl);
        CategoryEntity category = categoryRepository.findByName(categoryName).orElse(null);
        subcategory.setCategory(category);
        subcategory.setProducts(new HashSet<>());

        subcategoryRepository.save(subcategory);
    }

}
